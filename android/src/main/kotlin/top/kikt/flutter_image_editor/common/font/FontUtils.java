package top.kikt.flutter_image_editor.common.font;

import android.graphics.Typeface;
import android.os.Build;

import com.jaredrummler.truetypeparser.TTFFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FontUtils {
    private static int fontIndex = -1;
    private static Map<String, Typeface> map = new HashMap<>();

    public static String registerFont(String path) throws IOException {
        String fontName = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            TTFFile ttfFile = TTFFile.open(new FileInputStream(path));
            fontName = ttfFile.getFullName();
        }

        if (fontName == null) {
            fontIndex++;
            fontName = String.valueOf(fontIndex);
        }

        if (map.containsKey(fontName)) {
            return fontName;
        }

        Typeface typeface = Typeface.createFromFile(new File(path));
        map.put(fontName, typeface);
        return fontName;
    }


}