package io.dongyue.at15.tree.server;

import org.apache.hadoop.fs.FileStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.hadoop.fs.FsShell;

@SpringBootApplication
public class ServerApplication implements CommandLineRunner {
	@Autowired
	private FsShell shell;

    public void run(String... args) {
        for (FileStatus s : shell.lsr("/tmp")) {
            System.out.println("> " + s.getPath());
        }
    }

	public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
	}
}
