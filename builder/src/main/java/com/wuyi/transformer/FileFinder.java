package com.wuyi.transformer;

import com.wuyi.transformer.log.AdapterLogger;
import com.wuyi.transformer.log.Logger;

import java.io.File;

/**
 * Support to process Android relative files
 */
public class FileFinder {
    private static boolean strictMode = false;

    private static Logger logger = AdapterLogger.getInstance();

    /**
     * Get layout xml locate in {@link AndroidLocation#BASE_DIR_LAYOUT} by id.
     * @param layoutId the layout id
     * @return the layout xml file if exist, otherwise null
     */
    public static File layout(int layoutId) {
        String fileName = Id2NameResolver.layout(layoutId);
        String path = AndroidLocation.BASE_DIR_LAYOUT + fileName;
        File file = new File(path);
        if (!file.exists()) {
            String errorMsg = String.format("file[%s] no found!", path);
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
