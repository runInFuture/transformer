package com.wuyi.transformer;

import com.wuyi.transformer.log.AdapterLogger;
import com.wuyi.transformer.log.Logger;

import java.io.File;
import java.io.IOException;

/**
 * Support to process Android relative files
 */
public class FileFinder {
    private static boolean strictMode = false;

    private static Logger logger = AdapterLogger.getInstance();

    /**
     * Get layout xml locate in {@link com.wuyi.transformer.AndroidFiles.Location#PATH_DIR_LAYOUT} by id.
     * @param layoutId the layout id
     * @return the layout xml file if exist, otherwise null
     */
    public static File layout(int layoutId) {
        String fileName = AndroidFiles.getInstance().R.getLayoutName(layoutId);
        if (fileName == null)
            throw new RuntimeException(String.format("0x%s doesn't match any layout, please check the Layout Annotation", Integer.toHexString(layoutId)));
        String path = AndroidFiles.Location.PATH_DIR_LAYOUT + fileName + ".xml";
        return file(path);
    }

    /**
     * Get R.java file.
     * @return the R.java file
     */
    public static File R() {
        return file(AndroidFiles.Location.PATH_R);
    }

    /**
     * return AndroidManifest.xml file.
     * @return the AndroidManifest.xml file
     */
    public static File manifest() {
        return file(AndroidFiles.Location.PATH_ANDROID_MANIFEST);
    }

    private static File file(String path) {
        // fixme why the gradle module path lose?
        File file = new File("demo/" + path);
        if (!file.exists()) {
            String errorMsg = null;
            try {
                errorMsg = String.format("file[%s] no found!", file.getCanonicalPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
            // once print error output, gradle will fail...
//            logger.error(errorMsg);
            logger.info("error: " + errorMsg);
            if (strictMode) {
                raiseError(errorMsg);
            }
        }
        return file.exists() ? file : null;
    }

    private static void raiseError(String msg) {
        throw new IllegalArgumentException(msg);
    }
}
