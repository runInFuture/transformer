package com.wuyi.transformer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * Manager common android file、system relative paths
 */
public class AndroidFiles {
    private static AndroidFiles instance;
    public R R;

    public static AndroidFiles getInstance() {
        if (instance == null) {
            synchronized (AndroidFiles.class) {
                instance = new AndroidFiles();
            }
        }
        return instance;
    }

    private AndroidFiles() {
        R = new R();
    }

    public static class Location {
        public static final String PATH_DIR_RES = "src/main/res/";
        public static final String PATH_DIR_LAYOUT = PATH_DIR_RES + "layout/";
        public static final String PATH_ANDROID_MANIFEST = "src/main/";
        // fixme packageName need to be provide
//        public static final String PATH_R = "/src/main/java/com/wuyi/transformer/R.java";
        public static final String PATH_R = "build/generated/not_namespaced_r_class_sources/release/r/com/wuyi/transformer/R.java";
    }

    class R {
        private Map<Integer, String> id2layoutMap;

        R() {
            parseFile(FileFinder.R());
        }

        void parseFile(File file) {
            new SimpleTextRParser().parse(this, file);
        }

        public String getLayoutName(int layoutId) {
            return id2layoutMap != null ? id2layoutMap.get(layoutId) : null;
        }

        @Override
        public String toString() {
            return "layout: " + id2layoutMap;
        }
    }

    interface RParser {
        void parse(R r, File rFile);
    }

    // todo: use other parse class instead of read text. Consider JavaParser https://github.com/javaparser/javaparser
    class SimpleTextRParser implements RParser {
        @Override
        public void parse(AndroidFiles.R r, File rFile) {
            try {
                InputStreamReader read =
                        new InputStreamReader(new FileInputStream(rFile), StandardCharsets.UTF_8);
                BufferedReader bufferedReader = new BufferedReader(read);
                Map<Integer, String> layoutMap = new HashMap<>();
                Map<Integer, String> workMap = null;
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    if (line.trim().startsWith("public static final class layout {")) {
                        workMap = layoutMap;
                    } else {
                        if (line.contains("=")) {
                            if (workMap != null) {
                                String[] pair = line.split("=");
                                layoutMap.put(Integer.parseInt(pair[1].substring(2, pair[1].length() - 1), 16), pair[0].substring(pair[0].lastIndexOf(" ") + 1));
                            }
                        }
                    }
                }
                r.id2layoutMap = layoutMap;
                bufferedReader.close();
                read.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
