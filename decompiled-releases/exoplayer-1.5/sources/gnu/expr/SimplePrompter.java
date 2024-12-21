package gnu.expr;

import gnu.mapping.InPort;
import gnu.mapping.Procedure1;

/* compiled from: Language */
class SimplePrompter extends Procedure1 {
    public String prefix = "[";
    public String suffix = "] ";

    SimplePrompter() {
    }

    public Object apply1(Object arg) {
        int line;
        if (!(arg instanceof InPort) || (line = ((InPort) arg).getLineNumber() + 1) < 0) {
            return this.suffix;
        }
        return this.prefix + line + this.suffix;
    }
}
