package com.cpl.constants;

public class INICode {
	public static final String SPACE_KEY = "SPACE_KEY";//temp
	public static final String N = "N";
	public static final String P = "P";
	public static final String D = "D";
	
	public static final String NINE_PATCH_SUFFIX = "_9_patch";
	public static final String MSP_SUFFIX = ".msp";
	
	 /**
     * 空格键左右箭头特殊处理，因为空格键本身携带的信息不够，不好强加
     */
    public static final String ICON_KEY_SPACE_LEFT_ARROW = "ICON_KEY_SPACE_LEFT_ARROW";
    public static final String ICON_KEY_SPACE_RIGHT_ARROW = "ICON_KEY_SPACE_RIGHT_ARROW";
    /**
     * 回车键同空格键，本身单key携带的信息不够
     */
    public static final String ICON_KEY_ENTER_SEARCH = "ICON_KEY_ENTER_SEARCH";
    public static final String ICON_KEY_ENTER_NEXT = "ICON_KEY_ENTER_NEXT";
    public static final String ICON_KEY_ENTER_SEND = "ICON_KEY_ENTER_SEND";
    public static final String ICON_KEY_ENTER_HIGHLIGHT_BG = "ICON_KEY_ENTER_HIGHLIGHT_BG";

    public final static String KEYBOARD_SECTION = "KEYBOARD";
    public final static String KEYBOARD_NAME = "NAME";
    public final static String KEYBOARD_PAGES = "PAGES";
    public final static String PAGE_KEYS = "KEYS";
    public final static String BG = "BG";
    public final static String WIDHT = "W";
    public final static String HEIGHT = "H";
    public final static String TARGET_KEYBOARD = "TARGET_KEYBOARD";
    public final static String TIL_W = "TIL_W";

    public final static String POPUP_BG = "POPUP_BG";

    public final static String KEY_DEFAULT_SECTION = "KEY";
    public final static String KEY_DEFAULT_FUNC_SECTION = "KEY_FUNC";

    public final static String KEY_VIEW_RECT = "VIEW_RECT";
    public final static String KEY_TOUCH_RECT = "TOUCH_RECT";
    public final static String KEY_LABEL = "LABEL";
    public final static String KEY_MINOR_LABEL = "M_LABEL";
    public final static String KEY_CODE = "CODE";
    public final static String KEY_UNION_CODE = "UNION_CODE";
    public final static String KEY_SHIFT_CODE = "SF_CODE";
    public final static String KEY_CODES = "CODES";
    public final static String KEY_ICON = "ICON";
    public final static String KEY_S_CODE = "S_CODE";
//    public final static String KEY_LABEL_SIZE = "LABEL_SIZE";
    public final static String KEY_L_CODES = "L_CODES";
    public final static String KEY_L_FOCUS = "L_FOCUS";
    public final static String KEY_PREVIEW_SHOW = "POPUP";
    public final static String KEY_ICON_FONT = "ICON_FONT";
    public final static String KEY_SOUND = "SOUND";
    public final static String SOUND_NAME = "NAME";

    public final static String FOREGROUND_DEFAULT_SECTION = "DEFAULT_FOREGROUND";
    public final static String FOREGROUND = "FG_TYPE";
    public final static String POPUP_FG = "POPUP_FG";
    public final static String LABEL_X = "LABEL_X";
    public final static String LABEL_Y = "LABEL_Y";
    public final static String M_LABEL_X = "M_LABEL_X";
    public final static String M_LABEL_Y = "M_LABEL_Y";

    public final static String LABEL_STYLE = "LABEL_STYLE";
    public final static String M_LABEL_STYLE = "M_LABEL_STYLE";
    public final static String SIZE = "SIZE";
    public final static String TYPEFACE = "TYPEFACE";
    public final static String COLOR = "COLOR";
    public final static String P_COLOR = "P_COLOR";//press color
    public final static String H_COLOR = "H_COLOR";//highlight color
    public final static String S_COLOR = "S_COLOR";//select color
    public final static String D_COLOR = "D_COLOR";//disable color

    //candidates
    public final static String TOOLS_CANDIDATE = "TOOLS_CANDIDATE";//
    public final static String WORDS_CANDIDATE = "WORDS_CANDIDATE";//
    public final static String SIMPLE_TITLE_CANDIDATE = "SIMPLE_TITLE_CANDIDATE";//

    public final static String WINDOW_SECTION = "WINDOW";
    public final static String CANDS_BG = "CANDS_BG";
    public final static String MIN_H = "MIN_H";
    public final static String MIN_W = "MIN_W";
    public final static String MAX_H = "MAX_H";
    public final static String MAX_W = "MAX_W";
    public final static String CANDS_PORT_H = "CANDS_PORT_H";
    public final static String CANDS_LAND_H = "CANDS_LAND_H";

    /**keyboard symbol area*/
    public final static String SYMBOL_AREA = "SYMBOL_AREA";

    /** StatedImages : 0; Image : 1;*/
    public final static String IMAGE_TYPE = "TYPE";
    public final static String IMAGE = "IMAGE";

    /** Key state */
    public final static String DISABLED = "D";
    public final static String NORMAL = "N";
    public final static String PRESSED = "P";
    public static final String FOCUSED = "F";
    public static final String SELECTED = "S";

    /**Candidate icon section*/
    public static final String ICON_CAND_FUNCTIONBOX = "ICON_CAND_FUNCTIONBOX";
    public static final String ICON_CAND_EDIT = "ICON_CAND_EDIT";
    public static final String ICON_CAND_SWITCH = "ICON_CAND_SWITCH";
    public static final String ICON_CAND_CLEAR = "ICON_CAND_CLEAR";
    public static final String ICON_CAND_LEFT_BACK_ARROW = "ICON_CAND_LEFT_BACK_ARROW";

    /**Function icon section*/
    public static final String ICON_FUNCTION_THEME = "ICON_FUNCTION_THEME";
    public static final String ICON_FUNCTION_LANGUAGE = "ICON_FUNCTION_LANGUAGE";
    public static final String ICON_FUNCTION_CLIPBOARD = "ICON_FUNCTION_CLIPBOARD";
    public static final String ICON_FUNCTION_PHRASES = "ICON_FUNCTION_PHRASES";
    public static final String ICON_FUNCTION_NIGHT = "ICON_FUNCTION_NIGHT";
    public static final String ICON_FUNCTION_HIGH = "ICON_FUNCTION_HIGH";
    public static final String ICON_FUNCTION_FLOAT = "ICON_FUNCTION_FLOAT";
    public static final String ICON_FUNCTION_SET = "ICON_FUNCTION_SET";
    public static final String ICON_FUNCTION_VIBRATE = "ICON_FUNCTION_VIBRATE";
    public static final String ICON_FUNCTION_SOUND = "ICON_FUNCTION_SOUND";

    /**Edit panel icon section*/
    public static final String KEY_EDIT_SELECT_ALL = "KEY_EDIT_SELECT_ALL";
    public static final String KEY_EDIT_COPY = "KEY_EDIT_COPY";
    public static final String KEY_EDIT_PASTE = "KEY_EDIT_PASTE";
    public static final String KEY_EDIT_DELETE = "KEY_EDIT_DELETE";
    public static final String KEY_EDIT_SELECT = "KEY_EDIT_SELECT";
    public static final String KEY_EDIT_LEFT = "KEY_EDIT_LEFT";
    public static final String KEY_EDIT_RIGHT = "KEY_EDIT_RIGHT";
    public static final String KEY_EDIT_TOP = "KEY_EDIT_TOP";
    public static final String KEY_EDIT_BOTTOM = "KEY_EDIT_BOTTOM";
    public static final String KEY_EDIT_CUT = "KEY_EDIT_CUT";
    public static final String KEY_EDIT_START = "KEY_EDIT_START";
    public static final String KEY_EDIT_END = "KEY_EDIT_END";

    /**TILLING VIEW*/
    public static final String ICON_TILING_CLOSE = "ICON_TILING_CLOSE";
    public static final String ICON_TILING_MORE = "ICON_TILING_MORE";
    public static final String ICON_TILING_RETURN = "ICON_TILING_RETURN";
    public static final String ICON_TILING_WORDS = "ICON_TILING_WORDS";

    /**EXPRESSION VIEW*/
    public static final String ICON_EXPRESSION_BACK = "ICON_EXPRESSION_BACK";
    public static final String ICON_EXPRESSION_DEL = "ICON_EXPRESSION_DEL";
    public static final String ICON_EMOJI_MENU_TAB = "ICON_EMOJI_MENU_TAB";
    public static final String ICON_EMOTICON_MENU_TAB = "ICON_EMOTICON_MENU_TAB";

    /**
     * COLORS
     */
    public static final String COLORS = "COLORS";
    public static final String COLOR_FIR = "COLOR_FIR";//默认透明度100% 即 全不透明
    public static final String COLOR_SEC = "COLOR_SEC";//透明度60%
    public static final String COLOR_THI = "COLOR_THI";//透明度40%
    public static final String COLOR_FOU = "COLOR_FOU";//透明度20%
    public static final String COLOR_FIF = "COLOR_FIF";//透明度10%
    public static final String COLOR_SIX = "COLOR_SIX";//透明度5%
    public static final String COLOR_TAB_SELECT = "COLOR_TAB_SELECT";//工具条 TAB按下颜色
    public static final String COLOR_CANDS_HL = "COLOR_CANDS_HL";// 首候选高亮颜色

    public static final String ICON_SWITCH_KEYBOARD_ITEM = "ICON_SWITCH_KEYBOARD_ITEM";



    /**skin*/
    public static final String SKIN_GAUS = "SKIN_GAUS";

    /**Icon font*/
    public static final String TYPEFACE_ICONFONT_DEFAULT = "iconfont.ttf";
    public static final String IF_FLOAT_DRAG = "IF_FLOAT_DRAG";
    public static final String IF_RESIZE_HEIGHT_HANDLE = "IF_RESIZE_HEIGHT_HANDLE";
    public static final String IF_RESIZE_HEIGHT_COMPLATE = "IF_RESIZE_HEIGHT_COMPLATE";
    public static final String IF_RESIZE_HEIGHT_RESET = "IF_RESIZE_HEIGHT_RESET";
    public static final String IF_PHRASES_ADD = "IF_PHRASES_ADD";


    /**Animation*/
    public static final String ANIMATION_ANI = "ANI";
    public static final String ANIMATION_IMG = "IMG";
    public static final String ANIMATION_MOVE = "MOVE";
    public static final String ANIMATION_ALPHA = "ALPHA";
    public static final String ANIMATION_SCALE = "SCALE";
    public static final String ANIMATION_ROTATE = "ROTATE";
    public static final String ANIMATION_TYPE = "TYPE";
    public static final String ANIMATION_START_TIME = "START_TIME";
    public static final String ANIMATION_TOTAL_TIME = "TOTAL_TIME";
    public static final String ANIMATION_POINTS = "POINTS";
    public static final String ANIMATION_ORI_ALPHA = "ORI_ALPHA";
    public static final String ANIMATION_FIN_ALPHA = "FIN_ALPHA";
    public static final String ANIMATION_ORI_SIZE = "ORI_SIZE";
    public static final String ANIMATION_FIN_SIZE = "FIN_SIZE";
    public static final String ANIMATION_ORI_DEGREE = "ORI_DEGREE";
    public static final String ANIMATION_FIN_DEGREE = "FIN_DEGREE";

    
    public static final String SECTION_IMG_PRE = "IMG_";
    //todo cpl! fix the section
    public static final String SECTION_KEY = "KEY";
    public static final String SECTION_FUNC_KEY = "FUNC_KEY";
    public static final String SECTION_SPACE_KEY = "KEY_FUNC_SPACE";
    public static final String SECTION_ENTER_KEY = "KEY_FUNC_ENTER";
    public static final String SECTION_BACK_SAPCE_KEY = "KEY_FUNC_DEL";
    public static final String SECTION_SHIFT_KEY = "KEY_FUNC_SHIFT";
    public static final String SECTION_FUHAO_KEY = "KEY_FUNC_DIGIT";
    public static final String SECTION_DIGIT_KEY = "KEY_FUNC_SYMBOL";
    public static final String SECTION_EMOJI_KEY = "KEY_FUNC_EMOJI";
    public static final String SECTION_BG_SYMBOL_AREA = "BG_SYMBOL_AREA";
    //
    public static final String SECTION_KEY_SYM_1 = "KEY_SYM_1";
    public static final String SECTION_KEY_SYM_2 = "KEY_SYM_2";
    //
    public static final String SECTION_TEXT_DEF_SINGLE_STYLE = "TEXT_DEF_SINGLE_STYLE";
    public static final String SECTION_TEXT_DEF_DOUBLE_STYLE = "TEXT_DEF_DOUBLE_STYLE";
    public static final String SECTION_KB_BG = "KB_BG";
    public static final String SECTION_BG_CANDS = "BG_CANDS";
    public static final String SECTION_BG_KEY_POPUP = "BG_KEY_POPUP";
    //
    public static final String SECTION_KEY_EDIT_SELECT_ALL = "KEY_EDIT_SELECT_ALL";
    public static final String SECTION_KEY_EDIT_CUT = "KEY_EDIT_CUT";
    public static final String SECTION_KEY_EDIT_COPY = "KEY_EDIT_COPY";
    public static final String SECTION_KEY_EDIT_PASTE = "KEY_EDIT_PASTE";
    public static final String SECTION_KEY_EDIT_DELETE = "KEY_EDIT_DELETE";
    public static final String SECTION_KEY_EDIT_SELECT = "KEY_EDIT_SELECT";
    public static final String SECTION_KEY_EDIT_LEFT = "KEY_EDIT_LEFT";
    public static final String SECTION_KEY_EDIT_RIGHT = "KEY_EDIT_RIGHT";
    public static final String SECTION_KEY_EDIT_TOP = "KEY_EDIT_TOP";
    public static final String SECTION_KEY_EDIT_BOTTOM = "KEY_EDIT_BOTTOM";
    public static final String SECTION_KEY_EDIT_START = "KEY_EDIT_START";
    public static final String SECTION_KEY_EDIT_END = "KEY_EDIT_END";
    //
    

}
