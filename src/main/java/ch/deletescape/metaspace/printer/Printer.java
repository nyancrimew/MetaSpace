package ch.deletescape.metaspace.printer;

/**
 * Convenience class to statically access two default {@link MutablePrinter}
 * 
 * @author deletescape
 */
public final class Printer {
  private Printer() {}

  /**
   * {@link MutablePrinter} printing to {@link System#out}
   */
  public static final MutablePrinter out = new MutablePrinter(System.out);
  /**
   * {@link MutablePrinter} printing to {@link System#err}
   */
  public static final MutablePrinter err = new MutablePrinter(System.err);
  /**
   * {@link MutablePrinter} printing to {@link System#out}, initially muted
   */
  public static final MutablePrinter debug = new MutablePrinter(System.out);
  static {
    debug.mute(true);
  }

  public static void debug() {
    debug.mute(false);
  }
}
