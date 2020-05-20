package com.sftp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.file.remote.InputStreamCallback;
import org.springframework.integration.file.remote.RemoteFileTemplate;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.lang.NonNull;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import com.jcraft.jsch.ChannelSftp.LsEntry;

@Service
public class SftpService {

	@Autowired
	private RemoteFileTemplate<LsEntry> template;

	public void checkFileExist(String remoteDirectory, String fileName) {
		System.out.println(template.exists(remoteDirectory.concat(fileName)));
	}

	public void upload(@NonNull File file, Optional<String> subFolder) {
		Message<File> message = MessageBuilder.withPayload(file).build();
		if (subFolder.isPresent())
			System.out.println(template.send(message, subFolder.get(), FileExistsMode.IGNORE));
		else
			System.out.println(template.send(message, FileExistsMode.REPLACE));
	}

	public void uploadMultiple(List<File> files, String subfolder) {
		files.forEach(f -> {
			Message<File> message = MessageBuilder.withPayload(f).build();
			System.out.println(
					template.send(message, Objects.nonNull(subfolder) ? subfolder : "/", FileExistsMode.IGNORE));
		});

	}

	public void checkList(String folder) {
		LsEntry[] list = template.list(Objects.isNull(folder) ? "/" : folder);

		for (int i = 0; i < list.length; i++) {
			LsEntry entry = list[i];
			System.out.println(entry.getFilename());
		}
	}

	public void download() {

		final byte[] b = new byte[2048];
		template.get("/app/multiple/candace-leilani-2-1920x1080.jpg", new InputStreamCallback() {
			@Override
			public void doWithInputStream(InputStream is) throws IOException {
				try (OutputStream os = FileUtils.openOutputStream(new File("target/sftp-in/candace-leilani-2-1920x1080.jpg"),true)) {
					int length;
					while ((length = is.read(b)) != -1) {
						os.write(b, 0, length);
					}

				} catch (FileNotFoundException fe) {
					fe.printStackTrace();
				}

			}
		});
	}

}
