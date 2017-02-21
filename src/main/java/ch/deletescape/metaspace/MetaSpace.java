package ch.deletescape.metaspace;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

import ch.deletescape.metaspace.printer.Printer;

public class MetaSpace {
  @Parameter(names = { "-D", "--debug" }, description = "Debug mode")
  private boolean debug = false;

  public static void main(String[] args) {
    MetaSpace metaSpace = new MetaSpace();
    new JCommander(metaSpace, args);
    metaSpace.run();
  }

  private void run() {
    if (debug) {
      Printer.debug();
      Printer.debug.println("DEBUGGING: on");
    }
  }

}
