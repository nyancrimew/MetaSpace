package ch.deletescape.metaspace;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.RemoteAddCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.transport.URIish;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import ch.deletescape.metaspace.printer.Printer;

@Parameters(commandDescription = "Initialize a new MetaSpace")
public class InitCommand {
  @Parameter(names = { "-n", "--name" }, description = "Give your MetaSpace a name")
  private String name = "my_metaspace";
  @Parameter(names = "--remote", description = "Specify the main remote git repository to be used")
  private String remote;
  private Properties metaProperties = new Properties();

  public void run() {
    metaProperties.setProperty("metaspace.name", name);
    metaProperties.setProperty("metaspace.creationdatetime", String.valueOf(System.currentTimeMillis()));
    try {
      Git git = Git.init().call();
      FileWriter fw = new FileWriter(new File(".metaspace"));
      metaProperties.store(fw, null);
      if (remote != null) {
        RemoteAddCommand remoteAdd = git.remoteAdd();
        remoteAdd.setName(Constants.DEFAULT_REMOTE_NAME);
        remoteAdd.setUri(new URIish(remote));
        remoteAdd.call();
      }
      Printer.out.println("Successfully initialized MetaSpace \"%s\"", name);
    } catch (GitAPIException | IOException | URISyntaxException e) {
      Printer.err.println(e.toString());
    }
  }
}
