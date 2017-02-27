package ch.deletescape.metaspace;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

import com.beust.jcommander.Parameters;

import ch.deletescape.metaspace.printer.Printer;

@Parameters(commandDescription = "Initialize a new MetaSpace")
public class InitCommand {
  public void run() {
    try {
      Git.init().call();
    } catch (GitAPIException e) {
      Printer.err.println(e.toString());
    }
    Printer.out.println("Successfully initialized MetaSpace");
  }
}
