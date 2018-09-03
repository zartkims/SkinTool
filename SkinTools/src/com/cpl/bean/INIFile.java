package com.cpl.bean;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;

import com.cpl.utils.FileUtils;

/**
 * INIFile class provides methods for manipulating (Read/Write) windows ini files.
 *
 * @author Prasad P. Khandekar
 * @version 1.0
 * @since 1.0
 */
public final class INIFile {
    /**
     * Variable to hold the ini file name and full path
     */
    private String mstrFile;

    /**
     * Variable to hold the sections in an ini file.
     */
    private HashMap<String, INISection> mhmapSections;

    private final static int BUF_SIZE = 8192;

    private int isValid = 0;

    public static final int NO_CHECK_STATE = 0;
    public static final int ENABLE_STATE = 1;
    public static final int DISABLE_STATE = -1;


    /**
     * Create a iniFile object from the file named in the parameter.
     *
     * @param pstrPathAndName The full path and name of the ini file to be used.
     */
    public INIFile(String pstrPathAndName) {
//        this.mpropEnv = getEnvVars();
        this.mhmapSections = new HashMap<String, INISection>();
        this.mstrFile = pstrPathAndName;
        // Load the specified INI file. 
        if (checkFile(pstrPathAndName)) loadFile();
    }

    public INIFile(InputStream is, String pstrPathAndName) {
        if (is == null)
            return;
        this.mstrFile = pstrPathAndName;
        this.mhmapSections = new HashMap();
        try {
            loadFile(is);
        } finally {
            FileUtils.closeStream(is);
        }
    }

    public INIFile(InputStream is, INIFile iniFile) {
        if (iniFile == null || is == null)
            return;
        this.mstrFile = iniFile.mstrFile;
        this.mhmapSections = iniFile.getMhmapSections();
        try {
            loadFile(iniFile, is);
        } finally {
            FileUtils.closeStream(is);
        }
    }
 
/*------------------------------------------------------------------------------
 * Getters 
------------------------------------------------------------------------------*/

    public int getIsValid() {
        return isValid;
    }

    public void setIsValid(int valid) {
        isValid = valid;
    }

    public HashMap<String, INISection> getMhmapSections() {
        return mhmapSections;
    }

    /**
     * Returns the ini file name being used.
     *
     * @return the INI file name.
     */
    public String getFileName() {
        return this.mstrFile;
    }

    /**
     * Returns the specified string property from the specified section.
     *
     * @param pstrSection the INI section name.
     * @param pstrProp    the property to be retrieved.
     * @return the string property value.
     */
    public String getStringProperty(String pstrSection, String pstrProp) {
        String strRet = null;
        INIProperty objProp = null;
        INISection objSec = null;

        objSec = (INISection) this.mhmapSections.get(pstrSection);
        if (objSec != null) {
            objProp = objSec.getProperty(pstrProp);
            if (objProp != null) {
                strRet = objProp.getPropValue();
                objProp = null;
            }
            objSec = null;
        }
        return strRet;
    }

    /**
     * Returns the specified boolean property from the specified section.
     * This method considers the following values as boolean values.
     * <ol>
     * <li>YES/yes/Yes - boolean true</li>
     * <li>NO/no/No  - boolean false</li>
     * <li>1 - boolean true</li>
     * <li>0 - boolean false</li>
     * <li>TRUE/True/true - boolean true</li>
     * <li>FALSE/False/false - boolean false</li>
     * </ol>
     *
     * @param pstrSection the INI section name.
     * @param pstrProp    the property to be retrieved.
     * @return the boolean value
     */
    public Boolean getBooleanProperty(String pstrSection, String pstrProp) {
        Boolean blnRet = null;
        String strVal = null;
        INIProperty objProp = null;
        INISection objSec = null;

        objSec = (INISection) this.mhmapSections.get(pstrSection);
        if (objSec != null) {
            objProp = objSec.getProperty(pstrProp);
            if (objProp != null) {
                strVal = objProp.getPropValue().toUpperCase();
                if (strVal.equals("YES") || strVal.equals("TRUE") ||
                        strVal.equals("1")) {
                    blnRet = Boolean.TRUE;
                } else {
                    blnRet = Boolean.FALSE;
                }
                objProp = null;
            }
            objSec = null;
        }
        return blnRet;
    }

    /**
     * Returns the specified integer property from the specified section.
     *
     * @param pstrSection the INI section name.
     * @param pstrProp    the property to be retrieved.
     * @return the integer property value.
     */
    public Integer getIntegerProperty(String pstrSection, String pstrProp) {
        Integer intRet = null;
        String strVal = null;
        INIProperty objProp = null;
        INISection objSec = null;

        objSec = (INISection) this.mhmapSections.get(pstrSection);
        if (objSec != null) {
            objProp = objSec.getProperty(pstrProp);
            try {
                if (objProp != null) {
                    strVal = objProp.getPropValue();
                    if (strVal != null) intRet = Integer.decode(strVal);
                }
            } catch (NumberFormatException NFExIgnore) {
            } finally {
                if (objProp != null) objProp = null;
            }
            objSec = null;
        }
        return intRet;
    }

    /**
     * Returns the specified long property from the specified section.
     *
     * @param pstrSection the INI section name.
     * @param pstrProp    the property to be retrieved.
     * @return the long property value.
     */
    public Long getLongProperty(String pstrSection, String pstrProp) {
        Long lngRet = null;
        String strVal = null;
        INIProperty objProp = null;
        INISection objSec = null;

        objSec = (INISection) this.mhmapSections.get(pstrSection);
        if (objSec != null) {
            objProp = objSec.getProperty(pstrProp);
            try {
                if (objProp != null) {
                    strVal = objProp.getPropValue();
                    if (strVal != null) lngRet = Long.decode(strVal);
                }
            } catch (NumberFormatException NFExIgnore) {
            } finally {
                if (objProp != null) objProp = null;
            }
            objSec = null;
        }
        return lngRet;
    }

    /**
     * Returns the specified double property from the specified section.
     *
     * @param pstrSection the INI section name.
     * @param pstrProp    the property to be retrieved.
     * @return the double property value.
     */
    public Double getDoubleProperty(String pstrSection, String pstrProp) {
        Double dblRet = null;
        String strVal = null;
        INIProperty objProp = null;
        INISection objSec = null;

        objSec = (INISection) this.mhmapSections.get(pstrSection);
        if (objSec != null) {
            objProp = objSec.getProperty(pstrProp);
            try {
                if (objProp != null) {
                    strVal = objProp.getPropValue();
                    if (strVal != null) dblRet = Double.valueOf(strVal);
                }
            } catch (NumberFormatException NFExIgnore) {
            } finally {
                if (objProp != null) objProp = null;
            }
            objSec = null;
        }
        return dblRet;
    }

    /**
     * Returns the specified float property from the specified section.
     *
     * @param pstrSection the INI section name.
     * @param pstrProp    the property to be retrieved.
     * @return the float property value.
     */
    public Float getFloatProperty(String pstrSection, String pstrProp) {
        Float dblRet = null;
        String strVal = null;
        INIProperty objProp = null;
        INISection objSec = null;

        if (mhmapSections == null) return null;
        objSec = (INISection) this.mhmapSections.get(pstrSection);
        if (objSec != null) {
            objProp = objSec.getProperty(pstrProp);
            try {
                if (objProp != null) {
                    strVal = objProp.getPropValue();
                    if (strVal != null) {
                        dblRet = Float.valueOf(strVal);
                    }
                }
            } catch (NumberFormatException NFExIgnore) {
            } finally {
                if (objProp != null) {
                    objProp = null;
                }
            }
            objSec = null;
        }
        return dblRet;
    }
 
/*------------------------------------------------------------------------------
 * Setters 
------------------------------------------------------------------------------*/

    /**
     * Sets the comments associated with a section.
     *
     * @param pstrSection  the section name
     * @param pstrComments the comments.
     */
    public void addSection(String pstrSection, String pstrComments) {
        INISection objSec = null;

        objSec = (INISection) this.mhmapSections.get(pstrSection);
        if (objSec == null) {
            objSec = new INISection(pstrSection);
            this.mhmapSections.put(pstrSection, objSec);
        }
        objSec = null;
    }

    /**
     * Sets the specified string property.
     *
     * @param pstrSection the INI section name.
     * @param pstrProp    the property to be set.
     * @pstrVal the string value to be persisted
     */
    public void setStringProperty(String pstrSection, String pstrProp,
                                  String pstrVal, String pstrComments) {
        INISection objSec = null;

        objSec = (INISection) this.mhmapSections.get(pstrSection);
        if (objSec == null) {
            objSec = new INISection(pstrSection);
            this.mhmapSections.put(pstrSection, objSec);
        }
        objSec.setProperty(pstrProp, pstrVal);
    }

    /**
     * Sets the specified boolean property.
     *
     * @param pstrSection the INI section name.
     * @param pstrProp    the property to be set.
     * @param pblnVal     the boolean value to be persisted
     */
    public void setBooleanProperty(String pstrSection, String pstrProp,
                                   boolean pblnVal, String pstrComments) {
        INISection objSec = null;

        objSec = (INISection) this.mhmapSections.get(pstrSection);
        if (objSec == null) {
            objSec = new INISection(pstrSection);
            this.mhmapSections.put(pstrSection, objSec);
        }
        if (pblnVal)
            objSec.setProperty(pstrProp, "TRUE");
        else
            objSec.setProperty(pstrProp, "FALSE");
    }

    /**
     * Sets the specified integer property.
     *
     * @param pstrSection the INI section name.
     * @param pstrProp    the property to be set.
     * @param pintVal     the int property to be persisted.
     */
    public void setIntegerProperty(String pstrSection, String pstrProp,
                                   int pintVal, String pstrComments) {
        INISection objSec = null;

        objSec = (INISection) this.mhmapSections.get(pstrSection);
        if (objSec == null) {
            objSec = new INISection(pstrSection);
            this.mhmapSections.put(pstrSection, objSec);
        }
        objSec.setProperty(pstrProp, Integer.toString(pintVal));
    }

    /**
     * Sets the specified long property.
     *
     * @param pstrSection the INI section name.
     * @param pstrProp    the property to be set.
     * @param plngVal     the long value to be persisted.
     */
    public void setLongProperty(String pstrSection, String pstrProp,
                                long plngVal, String pstrComments) {
        INISection objSec = null;

        objSec = (INISection) this.mhmapSections.get(pstrSection);
        if (objSec == null) {
            objSec = new INISection(pstrSection);
            this.mhmapSections.put(pstrSection, objSec);
        }
        objSec.setProperty(pstrProp, Long.toString(plngVal));
    }

    /**
     * Sets the specified double property.
     *
     * @param pstrSection the INI section name.
     * @param pstrProp    the property to be set.
     * @param pdblVal     the double value to be persisted.
     */
    public void setDoubleProperty(String pstrSection, String pstrProp,
                                  double pdblVal, String pstrComments) {
        INISection objSec = null;

        objSec = (INISection) this.mhmapSections.get(pstrSection);
        if (objSec == null) {
            objSec = new INISection(pstrSection);
            this.mhmapSections.put(pstrSection, objSec);
        }
        objSec.setProperty(pstrProp, Double.toString(pdblVal));
    }

    /*------------------------------------------------------------------------------
     * Public methods
    ------------------------------------------------------------------------------*/
    public int getTotalSections() {
        return this.mhmapSections.size();
    }

    /**
     * Returns a string array containing names of all sections in INI file.
     *
     * @return the string array of section names
     */
    public String[] getAllSectionNames() {
        int iCntr = 0;
        Iterator iter = null;
        String[] arrRet = null;

        try {
            if (this.mhmapSections.size() > 0) {
                arrRet = new String[this.mhmapSections.size()];
                for (iter = this.mhmapSections.keySet().iterator(); ; iter.hasNext()) {
                    arrRet[iCntr] = (String) iter.next();
                    iCntr++;
                }
            }
        } catch (NoSuchElementException NSEExIgnore) {
        } finally {
            if (iter != null) iter = null;
        }
        return arrRet;
    }

    /**
     * Returns a string array containing names of all the properties under specified section.
     *
     * @param pstrSection the name of the section for which names of properties is to be retrieved.
     * @return the string array of property names.
     */
    public String[] getPropertyNames(String pstrSection) {
        String[] arrRet = null;
        INISection objSec = null;

        objSec = (INISection) this.mhmapSections.get(pstrSection);
        if (objSec != null) {
            arrRet = objSec.getPropNames();
            objSec = null;
        }
        return arrRet;
    }

    /**
     * Returns a map containing all the properties under specified section.
     *
     * @param pstrSection the name of the section for which properties are to be retrieved.
     * @return the map of properties.
     */
    public Map<String, INIProperty> getProperties(String pstrSection) {
        Map<String, INIProperty> hmRet = null;
        INISection objSec = null;

        objSec = (INISection) this.mhmapSections.get(pstrSection);
        if (objSec != null) {
            hmRet = objSec.getProperties();
            objSec = null;
        }
        return hmRet;
    }

    /**
     * Removed specified property from the specified section. If the specified
     * section or the property does not exist, does nothing.
     *
     * @param pstrSection the section name.
     * @param pstrProp    the name of the property to be removed.
     */
    public void removeProperty(String pstrSection, String pstrProp) {
        INISection objSec = null;

        objSec = (INISection) this.mhmapSections.get(pstrSection);
        if (objSec != null) {
            objSec.removeProperty(pstrProp);
            objSec = null;
        }
    }

    /**
     * Removes the specified section if one exists, otherwise does nothing.
     *
     * @param pstrSection the name of the section to be removed.
     */
    public void removeSection(String pstrSection) {
        if (this.mhmapSections.containsKey(pstrSection))
            this.mhmapSections.remove(pstrSection);
    }

    /**
     * Flush changes back to the disk file. If the disk file does not exists then
     * creates the new one.
     */
    public boolean save() {
//        boolean blnRet = false;
//        File objFile = null;
//        String strName = null;
//        String strTemp = null;
//        Iterator itrSec = null;
//        INISection objSec = null;
//        FileWriter objWriter = null;
//
//        try {
//            if (this.mhmapSections.size() == 0) return false;
//            objFile = new File(this.mstrFile);
//            System.out.println("file path : " + objFile.getAbsolutePath());
//            if (objFile.exists()) objFile.delete();
//            objWriter = new FileWriter(objFile);
//            itrSec = this.mhmapSections.keySet().iterator();
//            while (itrSec.hasNext()) {
//                strName = (String) itrSec.next();
//                objSec = (INISection) this.mhmapSections.get(strName);
//                strTemp = objSec.toString();
//                objWriter.write(strTemp);
//                objWriter.write("\r\n");
//                System.out.println("write : " + strTemp);
//                objSec = null;
//            }
//            blnRet = true;
//        } catch (IOException IOExIgnore) {
//        	IOExIgnore.printStackTrace();
//        } finally {
//            if (objWriter != null) {
//                closeWriter(objWriter);
//                objWriter = null;
//            }
//            if (objFile != null) objFile = null;
//            if (itrSec != null) itrSec = null;
//        }
//        
        
        
		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			fw = new FileWriter(new File(mstrFile));
			bw = new BufferedWriter(fw);
			for (Entry<String, INISection> entry : mhmapSections.entrySet()) {
				String section = entry.getKey();
				INISection props = entry.getValue();
				bw.write("\r\n" + "[" + section + "]" + "\r\n");
				System.out.println("[" + section + "]");
				for (Entry<String, INIProperty> ps : props.mhmapProps.entrySet()) {
					INIProperty pro = ps.getValue();
					bw.write(pro.mstrName + "=" + pro.mstrValue + "\r\n");
					System.out.println(pro.mstrName + "=" + pro.mstrValue + "\r\n");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			FileUtils.closeStream(bw);
			FileUtils.closeStream(fw);
		}
        
        return true;
    }

/*------------------------------------------------------------------------------
 * Public methods
 ------------------------------------------------------------------------------*/

    /**
     * Returns
     *
     * @param pstrSection the section to be retrieved.
     * @return the boolean value
     */
    public boolean containSection(String pstrSection) {
        if (mhmapSections == null) return false;
        return this.mhmapSections.containsKey(pstrSection);
    }

    /**
     * Returns
     *
     * @param pstrProp the property to be retrieved.
     * @return the boolean value
     */
    public boolean containProperty(String pstrSection, String pstrProp) {
        INISection objSec = null;
        boolean ret = false;
        if (mhmapSections == null) return false;
        objSec = (INISection) this.mhmapSections.get(pstrSection);
        if (objSec != null) {
            ret = objSec.containProperty(pstrProp);
            objSec = null;
        }
        return ret;
    }

    /**
     * Reads the INI file and load its contentens into a section collection after
     * parsing the file line by line.
     */
    private void loadFile(InputStream inputStream) {
        int iPos = -1;
        String strLine = null;
        String strSection = null;

        BufferedReader objBRdr = null;
        InputStreamReader isr = null;
        INISection objSec = null;

        try {
            isr = new InputStreamReader(inputStream);
            if (isr != null) {
                objBRdr = new BufferedReader(isr, BUF_SIZE);
                if (objBRdr != null) {
                    while (objBRdr.ready()) {
                        iPos = -1;
                        strLine = null;
                        strLine = objBRdr.readLine();
                        if (strLine != null) {
                            strLine = strLine.trim();
                        }
                        if (strLine == null || strLine.length() == 0 || strLine.charAt(0) == ';') {
                        } else if (strLine.startsWith("[") && strLine.endsWith("]")) {
                            // Section start reached create new section
                            if (objSec != null)
                                this.mhmapSections.put(strSection, objSec);
                            objSec = null;
                            strSection = strLine.substring(1, strLine.length() - 1).trim();
                            objSec = new INISection(strSection);
                        } else if ((iPos = strLine.indexOf("=")) > 0 && objSec != null) {
                            // read the key value pair 012345=789
                            objSec.setProperty(strLine.substring(0, iPos).trim(),
                                    strLine.substring(iPos + 1).trim());
                        }
                    }
                    if (objSec != null)
                        this.mhmapSections.put(strSection, objSec);
                }
            }
        } catch (FileNotFoundException FNFExIgnore) {
            this.mhmapSections.clear();
        } catch (IOException IOExIgnore) {
            this.mhmapSections.clear();
        } catch (NullPointerException NPExIgnore) {
            this.mhmapSections.clear();
        } finally {
            if (objBRdr != null) {
                closeReader(objBRdr);
                objBRdr = null;
            }
            if (isr != null) {
                closeReader(isr);
                isr = null;
            }
            if (objSec != null) objSec = null;
        }
    }

    /**
     * Reads the INI file and load its contentens into a section collection after
     * parsing the file line by line.
     */
    private void loadFile(INIFile iniFile, InputStream inputStream) {
        int iPos = -1;
        String strLine = null;
        String strSection = null;

        BufferedReader objBRdr = null;
        InputStreamReader isr = null;
        INISection objSec = null;

        try {
            isr = new InputStreamReader(inputStream);
            if (isr != null) {
                objBRdr = new BufferedReader(isr, BUF_SIZE);
                if (objBRdr != null) {
                    while (objBRdr.ready()) {
                        iPos = -1;
                        strLine = null;
                        strLine = objBRdr.readLine();
                        if (strLine != null) {
                            strLine = strLine.trim();
                        }
                        if (strLine == null || strLine.length() == 0 || strLine.charAt(0) == ';') {
                        } else if (strLine.startsWith("[") && strLine.endsWith("]")) {
                            // Section start reached create new section
                            if (objSec != null)
                                this.mhmapSections.put(strSection, objSec);
                            objSec = null;
                            strSection = strLine.substring(1, strLine.length() - 1).trim();
                            objSec = (INISection) iniFile.getMhmapSections().get(strSection);
                            if (objSec == null) objSec = new INISection(strSection);
                        } else if ((iPos = strLine.indexOf("=")) > 0 && objSec != null) {
                            // read the key value pair 012345=789
                            objSec.setProperty(strLine.substring(0, iPos).trim(),
                                    strLine.substring(iPos + 1).trim());
                        }
                    }
                    if (objSec != null)
                        this.mhmapSections.put(strSection, objSec);
                }
            }
        } catch (FileNotFoundException FNFExIgnore) {
            this.mhmapSections.clear();
        } catch (IOException IOExIgnore) {
            this.mhmapSections.clear();
        } catch (NullPointerException NPExIgnore) {
            this.mhmapSections.clear();
        } finally {
            if (objBRdr != null) {
                closeReader(objBRdr);
                objBRdr = null;
            }
            if (isr != null) {
                closeReader(isr);
                isr = null;
            }
            if (objSec != null) objSec = null;
        }
    }

    /**
     * Reads the INI file and load its contentens into a section collection after
     * parsing the file line by line.
     */
    private void loadFile() {
        int iPos = -1;
        String strLine = null;
        String strSection = null;
        BufferedReader objBRdr = null;
        FileReader objFRdr = null;
        INISection objSec = null;

        try {
            objFRdr = new FileReader(this.mstrFile);
            if (objFRdr != null) {
                objBRdr = new BufferedReader(objFRdr, BUF_SIZE);
                if (objBRdr != null) {
                    while (objBRdr.ready()) {
                        iPos = -1;
                        strLine = null;
                        strLine = objBRdr.readLine();
                        if (strLine != null) {
                            strLine = strLine.trim();
                        }
                        if (strLine == null || strLine.length() == 0 || strLine.charAt(0) == ';') {
                        } else if (strLine.startsWith("[") && strLine.endsWith("]")) {
                            // Section start reached create new section 
                            if (objSec != null)
                                this.mhmapSections.put(strSection, objSec);
                            objSec = null;
                            strSection = strLine.substring(1, strLine.length() - 1).trim();
                            objSec = new INISection(strSection);
                        } else if ((iPos = strLine.indexOf("=")) > 0 && objSec != null) {
                            // read the key value pair 012345=789 
                            objSec.setProperty(strLine.substring(0, iPos).trim(),
                                    strLine.substring(iPos + 1).trim());
                        }
                    }
                    if (objSec != null)
                        this.mhmapSections.put(strSection.trim(), objSec);
                }
            }
        } catch (FileNotFoundException FNFExIgnore) {
            this.mhmapSections.clear();
        } catch (IOException IOExIgnore) {
            this.mhmapSections.clear();
        } catch (NullPointerException NPExIgnore) {
            this.mhmapSections.clear();
        } finally {
            if (objBRdr != null) {
                closeReader(objBRdr);
                objBRdr = null;
            }
            if (objFRdr != null) {
                closeReader(objFRdr);
                objFRdr = null;
            }
            if (objSec != null) objSec = null;
        }
    }

    /**
     * Helper function to close a reader object.
     *
     * @param pobjRdr the reader to be closed.
     */
    private void closeReader(Reader pobjRdr) {
        if (pobjRdr == null) return;
        try {
            pobjRdr.close();
        } catch (IOException IOExIgnore) {
        }
    }

    /**
     * Helper function to close a writer object.
     *
     * @param pobjWriter the writer to be closed.
     */
    private void closeWriter(Writer pobjWriter) {
        if (pobjWriter == null) return;

        try {
            pobjWriter.close();
        } catch (IOException IOExIgnore) {
        }
    }

    /**
     * Helper method to check the existance of a file.
     *
     * @param pstrFile the full path and name of the file to be checked.
     * @return true if file exists, false otherwise.
     */
    private boolean checkFile(String pstrFile) {
        boolean blnRet = false;
        File objFile = null;

        try {
            objFile = new File(pstrFile);
            blnRet = (objFile.exists() && objFile.isFile());
        } catch (Exception e) {
            blnRet = false;
        } finally {
            if (objFile != null) objFile = null;
        }
        return blnRet;
    }


    /*------------------------------------------------------------------------------
     * Private class representing the INI Section.
     *----------------------------------------------------------------------------*/

    /**
     * Class to represent the individual ini file section.
     *
     * @author Prasad P. Khandekar
     * @version 1.0
     * @since 1.0
     */
    public class INISection {
        /**
         * Variable to hold the section name.
         */
        private String mstrName;

        /**
         * Variable to hold the properties falling under this section.
         */
        private HashMap<String, INIProperty> mhmapProps;

        /**
         * Construct a new section object identified by the name specified in
         * parameter.
         *
         * @param pstrSection The new sections name.
         */
        public INISection(String pstrSection) {
            this.mstrName = pstrSection;
            this.mhmapProps = new HashMap<String, INIProperty>();
        }

        /**
         * Returns name of the section.
         *
         * @return Name of the section.
         */
        public String getSecName() {
            return this.mstrName;
        }

        /**
         * Removes specified property value from this section.
         *
         * @param pstrProp The name of the property to be removed.
         */
        public void removeProperty(String pstrProp) {
            if (this.mhmapProps.containsKey(pstrProp))
                this.mhmapProps.remove(pstrProp);
        }

        /**
         * Creates or modifies the specified property value.
         *
         * @param pstrProp  The name of the property to be created or modified.
         * @param pstrValue The new value for the property.
         */
        public void setProperty(String pstrProp, String pstrValue) {
            this.mhmapProps.put(pstrProp, new INIProperty(pstrProp, pstrValue));
        }

        /**
         * Returns a map of all properties.
         *
         * @return a map of all properties
         */
        public Map<String, INIProperty> getProperties() {
            return /*Collections.unmodifiableMap*/(this.mhmapProps);
        }

        /**
         * Returns a string array containing names of all the properties under
         * this section.
         *
         * @return the string array of property names.
         */
        public String[] getPropNames() {
            int iCntr = 0;
            String[] arrRet = null;
            Iterator iter = null;

            try {
                if (this.mhmapProps.size() > 0) {
                    arrRet = new String[this.mhmapProps.size()];
                    for (iter = this.mhmapProps.keySet().iterator(); iter.hasNext(); ) {
                        arrRet[iCntr] = (String) iter.next();
                        iCntr++;
                    }
                }
            } catch (NoSuchElementException NSEExIgnore) {
                arrRet = null;
            }
            return arrRet;
        }

        /**
         * Returns underlying value of the specified property.
         *
         * @param pstrProp the property whose underlying value is to be etrieved.
         * @return the property value.
         */
        public INIProperty getProperty(String pstrProp) {
            INIProperty objRet = null;

            if (this.mhmapProps.containsKey(pstrProp))
                objRet = (INIProperty) this.mhmapProps.get(pstrProp);
            return objRet;
        }

        /**
         * Returns
         *
         * @param pstrProp the property to be retrieved.
         * @return the boolean value
         */
        public boolean containProperty(String pstrProp) {
            return this.mhmapProps.containsKey(pstrProp);
        }

        /* (non-Javadoc)
         * @see java.lang.Object#toString()
         */
        public String toString() {
            Set colKeys = null;
            String strRet = "";
            Iterator iter = null;
            INIProperty objProp = null;
            StringBuffer objBuf = new StringBuffer();

            objBuf.append("[" + this.mstrName + "]\r\n");
            colKeys = this.mhmapProps.keySet();
            if (colKeys != null) {
                iter = colKeys.iterator();
                if (iter != null) {
                    while (iter.hasNext()) {
                        objProp = (INIProperty) this.mhmapProps.get(iter.next());
                        objBuf.append(objProp.toString());
                        objBuf.append("\r\n");
                        objProp = null;
                    }
                }
            }
            strRet = objBuf.toString();

            objBuf = null;
            iter = null;
            colKeys = null;
            return strRet;
        }
    }

    /*------------------------------------------------------------------------------
     * Private class representing the INI Property.
     *----------------------------------------------------------------------------*/

    /**
     * This class represents a key value pair called property in an INI file.
     *
     * @author Prasad P. Khandekar
     * @version 1.0
     * @since 1.0
     */
    public static class INIProperty {
        /**
         * Variable to hold name of this property
         */
        private String mstrName;
        /**
         * Variable to hold value of this property
         */
        private String mstrValue;

        /**
         * Constructor
         *
         * @param pstrName  the name of this property.
         * @param pstrValue the value of this property.
         */
        public INIProperty(String pstrName, String pstrValue) {
            this.mstrName = pstrName;
            this.mstrValue = pstrValue;
        }

        /**
         * Returns the string identifier (key part) of this property.
         *
         * @return the string identifier of this property.
         */
        public String getPropName() {
            return this.mstrName;
        }

        /**
         * Returns value of this property. If value contains a reference to
         * environment avriable then this reference is replaced by actual value
         * before the value is returned.
         *
         * @return the value of this property.
         */
        public String getPropValue() {
            String strRet = this.mstrValue;
            return strRet;
        }

        /**
         * Sets the string identifier (key part) of a property
         *
         * @param pstrName the string identifier of a property
         */
        public void setPropName(String pstrName) {
            this.mstrName = pstrName;
        }

        /**
         * Sets the property value
         *
         * @param pstrValue the value for the property
         */
        public void setPropValue(String pstrValue) {
            this.mstrValue = pstrValue;
        }

        /* (non-Javadoc)
         * @see java.lang.Object#toString()
         */
        public String toString() {
            return this.mstrName + "=" + this.mstrValue;
        }
    }
    
 
}
