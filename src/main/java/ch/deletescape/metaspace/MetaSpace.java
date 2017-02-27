package ch.deletescape.metaspace;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

import ch.deletescape.metaspace.printer.Printer;

public class MetaSpace {
  @Parameter(names = { "-D", "--debug" }, description = "Debug mode")
  private boolean debug = false;
  @Parameter(names = { "-q", "--quiet" }, description = "Mute all output")
  private boolean quiet = false;
  @Parameter(names = { "--usage", "--help" }, description = "Display usage information", help = true)
  private boolean usage = false;

  public static void main(String[] args) {
    MetaSpace metaSpace = new MetaSpace();
    InitCommand init = new InitCommand();
    JCommander jc = new JCommander(metaSpace);

    jc.addCommand("init", init);
    jc.parse(args);

    Printer.debug.mute(!metaSpace.debug || metaSpace.quiet);
    Printer.out.mute(metaSpace.quiet);

    if (metaSpace.usage) {
      usage(jc);
      return;
    }

    switch (jc.getParsedCommand()) {
      case "init":
        init.run();
        break;
      default:
        metaSpace.run();
    }
  }

  private void run() {
    Printer.out.println("MetaSpace - A space transcending ordinary physical space");
  }

  private static void usage(JCommander jc) {
    jc.setProgramName("metaspace");
    StringBuilder sb = new StringBuilder();
    jc.usage(sb);
    Printer.out.println(sb);
  }
}
