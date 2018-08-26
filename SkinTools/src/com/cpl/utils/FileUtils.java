package com.cpl.utils;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Set;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * @author cpl
 *
 */
public class FileUtils {
	private FileUtils() {

	}

	public static void closeStream(Closeable stream) {
		try {
			if (stream != null) {
				stream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 把流写入path
	 *
	 * @param is
	 * @param path
	 */
	public static boolean writeStream2Path(InputStream is, String path) {
		if (is == null || "".equals(path)) {
			return false;
		}
		File file = new File(path);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				return false;
			}
		}
		BufferedInputStream bis = null;

		FileOutputStream os = null;
		BufferedOutputStream bos = null;
		try {

			bis = new BufferedInputStream(is);

			os = new FileOutputStream(file);
			bos = new BufferedOutputStream(os);
			byte[] buf = new byte[1024];
			int len = -1;
			while ((len = bis.read(buf)) != -1) {
				bos.write(buf, 0, len);
			}
			bos.flush();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			closeStream(bos);
			closeStream(os);
			closeStream(bis);
			closeStream(is);
		}

	}

	/**
	 * this method will close the inputstream after read the file
	 *
	 * @param inputStream
	 * @return
	 */
	public static String readStringFromFile(InputStream inputStream) {
		StringWriter stringWriter = new StringWriter();
		InputStreamReader ir = null;
		try {
			ir = new InputStreamReader(inputStream);
			char[] cs = new char[1024 * 8];
			int n = -1;
			while ((n = ir.read(cs)) != -1) {
				stringWriter.write(cs, 0, n);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			FileUtils.closeStream(inputStream);
			FileUtils.closeStream(ir);
		}
		return stringWriter.toString();
	}

	public static String readStringFromFile(String path) {
		if ("".equals(path)) {
			return null;
		}
		File file = new File(path);
		return readStringFromFile(file);
	}

	public static String readStringFromFile(File file) {
		if (!file.exists())
			return null;
		FileInputStream fi = null;
		try {
			fi = new FileInputStream(file);
			return readStringFromInputStream(fi);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			FileUtils.closeStream(fi);
		}
		return null;
	}

	private static String readStringFromInputStream(InputStream inputStream) {
		InputStreamReader ir = null;
		StringWriter stringWriter = new StringWriter();
		try {
			ir = new InputStreamReader(inputStream);
			char[] cs = new char[1024 * 8];
			int n = -1;
			while ((n = ir.read(cs)) != -1) {
				stringWriter.write(cs, 0, n);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			FileUtils.closeStream(ir);
			closeStream(inputStream);
		}
		return stringWriter.toString();
	}

	public static final boolean writeStringToFile(String src, String filePath) {
		if ("".equals(filePath)) {
			return false;
		}
		return writeStringToFile(src, new File(filePath));
	}

	private static boolean writeStringToFile(String src, File destFile, boolean append) {
		boolean flag = true;
		BufferedReader br = null;
		RandomAccessFile raf = null;
		BufferedWriter bw = null;
		try {
			if (destFile.getParentFile() != null && !destFile.getParentFile().exists()) {
				destFile.getParentFile().mkdirs();
			}
			if (append) {
				raf = new RandomAccessFile(destFile, "rw");
				raf.seek(destFile.length());
				raf.write(src.getBytes());
			} else {
				br = new BufferedReader(new StringReader(src));
				bw = new BufferedWriter(new FileWriter(destFile));
				char buf[] = new char[1024];
				int len;
				while ((len = br.read(buf)) != -1) {
					bw.write(buf, 0, len);
				}
				bw.flush();
			}
		} catch (Exception e) {
			flag = false;
			return flag;
		} finally {
			FileUtils.closeStream(bw);
			FileUtils.closeStream(raf);
			FileUtils.closeStream(br);
		}
		return flag;

	}

	public static final boolean writeStringToFile(String src, File destFile) {
		return writeStringToFile(src, destFile, false);
	}

	public static final boolean appendStringToFile(String src, File destFile) {
		return writeStringToFile(src, destFile, true);
	}

	public static void traverseSDPath(Set<String> set, String rootPath) {
		traverseSDPath(set, rootPath, new StringBuilder());
	}

	private static void traverseSDPath(Set<String> set, String rootPath, StringBuilder sb) {
		if ("".equals(rootPath) || set == null)
			return;
		try {
			String[] files = new File(rootPath).list();
			for (String file : files) {
				sb.setLength(0);
				sb.append(rootPath).append('/').append(file);
				set.add(sb.toString());
				if (!file.contains(".")) {
					traverseSDPath(set, sb.toString(), sb);
				}
			}
		} catch (Exception e) {

		}
	}

	public static boolean gzip(String srcPath, String desPath) {
		try {
			if ("".equals(srcPath) || "".equals(desPath))
				return false;
			File srcFile = new File(srcPath);
			File desFile = new File(desPath);
			return gzip(srcFile, desFile);
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean gzip(File srcFile, File desFile) {
		GZIPOutputStream zos = null;
		FileInputStream fis = null;
		try {
			if (srcFile == null || !srcFile.exists())
				return false;
			if (!desFile.exists())
				desFile.createNewFile();
			zos = new GZIPOutputStream(new FileOutputStream(desFile));
			fis = new FileInputStream(srcFile);
			byte[] buffer = new byte[1024];
			int len = -1;
			while ((len = (fis.read(buffer))) != -1) {
				zos.write(buffer, 0, len);
			}
			return true;
		} catch (IOException e) {
			return false;
		} finally {
			closeStream(zos);
			closeStream(fis);
		}
	}

	public static boolean deleteFile(String path) {
		if ("".equals(path))
			return false;
		File file = new File(path);
		return deleteFile(file);
	}

	public static boolean deleteFile(File file) {
		if (file != null) {
			if (!file.exists())
				return true;
			if (file.isDirectory()) {
				clearDir(file);
			}
			boolean d = file.delete();
			return d;
		}
		return false;
	}

	public static void clearDir(File dir) {
		if (dir != null) {
			File[] files = dir.listFiles();
			if (files != null) {
				for (File file : files) {
					deleteFile(file);
				}
			}
		}
	}

	public static boolean createDir(String path) {
		return createDir(path, false);
	}

	/**
	 *
	 * @param path
	 * @param isForce
	 *            如果有同名文件而非文件夹是否删除该文件并创建文件夹
	 * @return
	 */
	public static boolean createDir(String path, boolean isForce) {
		File file = new File(path);
		if (!file.exists())
			return file.mkdirs();
		if (file.isDirectory()) {
			return true;
		}
		if (isForce) {
			file.delete();
			return file.mkdirs();
		}
		return false;
	}

	public static void zipFile(String srcPath, String targetPath) {
		if ("".equals(srcPath) || "".equals(targetPath))
			return;
		FileOutputStream fos = null;
		ZipOutputStream zos = null;
		try {
			File srcFile = new File(srcPath);
			if (!srcFile.exists())
				return;
			fos = new FileOutputStream(targetPath);
			zos = new ZipOutputStream(fos);
			String baseDirName = new File(targetPath).getName();
			zipFile(srcPath, baseDirName.substring(0, baseDirName.lastIndexOf(".")), zos);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		} finally {
			closeStream(zos);
			closeStream(fos);
		}
		return;
	}

	private static void zipFile(String srcPath, String targetPath, ZipOutputStream zos) {
		if ("".equals(srcPath) || "".equals(targetPath))
			return;
		FileInputStream fi = null;
		BufferedInputStream in = null;
		ZipEntry zipEntry = null;

		try {
			File srcFile = new File(srcPath);
			if (!srcFile.exists())
				return;
			if (srcFile.isDirectory()) {
				File[] children = srcFile.listFiles();
				for (File child : children) {
					zipFile(child.getPath(), targetPath + File.separator + child.getName(), zos);
				}
			} else {
				zipEntry = new ZipEntry(targetPath);
				fi = new FileInputStream(srcFile);
				in = new BufferedInputStream(fi);
				zos.putNextEntry(zipEntry);
				byte[] buffer = new byte[2 * 1024];
				int len = 0;
				while ((len = in.read(buffer)) != -1) {
					zos.write(buffer, 0, len);
				}
				zos.closeEntry();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return;
		} finally {
			closeStream(fi);
			closeStream(in);
		}
		return;
	}

	/**
	 * 不解压zip直接从zip文件中读取指定文件的内容作为 string返回
	 * 
	 * @param zipFilePath
	 *            /xxx/xxx/yourName.zip
	 * @param targetFilePath
	 *            相对与zip根目录的相对路径 yourName/yourFile
	 * @return
	 */
	public static String readStringFromZip(String zipFilePath, String targetFilePath) {
		InputStream is = getInputStreamFromZip(zipFilePath, targetFilePath);
		if (is == null)
			return null;
		String res = readStringFromInputStream(is);
		closeStream(is);
		return res;
	}

	/**
	 * 不解压zip直接从zip文件中读取指定文件的内容作为 string返回
	 * 
	 * @param zipFilePath
	 *            /xxx/xxx/yourName.zip
	 * @param targetFilePath
	 *            相对与zip根目录的相对路径 yourName/yourFile
	 * @return
	 */
	public static InputStream getInputStreamFromZip(String zipFilePath, String targetFilePath) {
		if ("".equals(zipFilePath) || "".equals(targetFilePath))
			return null;
		ZipInputStream zin = null;
		FileInputStream fin = null;
		ZipEntry ze;
		ZipFile zipFile = null;
		boolean hasTargetStream = false;
		try {
			zipFile = new ZipFile(zipFilePath);
			fin = new FileInputStream(zipFilePath);
			zin = new ZipInputStream(fin);
			while ((ze = zin.getNextEntry()) != null) {
				if (!ze.isDirectory()) {
					if (ze.getName().equals(targetFilePath)) {
						hasTargetStream = true;
						return zipFile.getInputStream(ze);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (!hasTargetStream) {
				closeStream(fin);
				closeStream(zin);
			}

		}
		return null;
	}

	public static boolean extractZipFile(String srcPath, String targetPath) {
		if ("".equals(srcPath) || "".equals(targetPath))
			return false;
		FileInputStream fin = null;
		ZipInputStream zin = null;
		ZipEntry zipEntry = null;

		try {
			fin = new FileInputStream(srcPath);
			zin = new ZipInputStream(fin);
			while ((zipEntry = zin.getNextEntry()) != null) {
				String entryName = zipEntry.getName();

				if (zipEntry.isDirectory()) {
					File zFile = new File(targetPath + entryName);
					if (!zFile.exists())
						zFile.mkdirs();
					zin.closeEntry();
				} else {
					File zFile = new File(targetPath + entryName);
					File pFile = new File(zFile.getParentFile().getPath());
					if (!pFile.exists()) {
						pFile.mkdirs();
					}
					FileOutputStream fou = new FileOutputStream(zFile);
					int len = -1;
					byte[] buffer = new byte[1024];
					while ((len = zin.read(buffer)) != -1) {
						fou.write(buffer, 0, len);
					}
					zin.closeEntry();
					fou.close();
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			closeStream(fin);
			closeStream(zin);
		}
	}

}

