package ch.deletescape.metaspace;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import ch.deletescape.metaspace.printer.Printer;

@Parameters(commandDescription = "Initialize a new MetaSpace")
public class InitCommand {
  @Parameter(names = { "-n", "--name" }, description = "Give your MetaSpace a name")
  private String name = "my_metaspace";
  private Properties metaProperties = new Properties();

  public void run() {
    metaProperties.setProperty("metaspace.name", name);
    metaProperties.setProperty("metaspace.creationdatetime", String.valueOf(System.currentTimeMillis()));
    try {
      Git.init().call();
      FileWriter fw = new FileWriter(new File(".metaspace"));
      metaProperties.store(fw, null);
      Printer.out.println("Successfully initialized MetaSpace \"%s\"", name);
    } catch (GitAPIException | IOException e) {
      Printer.err.println(e.toString());
    }
  }
}
