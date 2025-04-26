package jwn.screenshot_by_day.mixin;

import net.minecraft.client.util.ScreenshotRecorder;
import net.minecraft.util.Util;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Mixin(ScreenshotRecorder.class)
public class ScreenshotRecorderMixin {
	@Overwrite
	private static File getScreenshotFilename(File directory) {
		String today = LocalDate.now().format(DateTimeFormatter.ISO_DATE); // 예: "2025-03-27"
		File datedDir = new File(directory, today);
		datedDir.mkdir();

		String baseName = Util.getFormattedCurrentTime(); // "2025-03-27_12.34.56"
		int i = 1;

		while (true) {
			File file = new File(datedDir, baseName + (i == 1 ? "" : "_" + i) + ".png");
			if (!file.exists()) {
				return file;
			}
			++i;
		}
	}
}