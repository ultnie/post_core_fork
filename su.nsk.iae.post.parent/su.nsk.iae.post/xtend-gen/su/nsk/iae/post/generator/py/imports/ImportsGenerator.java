package su.nsk.iae.post.generator.py.imports;

import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenation;
import su.nsk.iae.post.poST.Inline_code;

@SuppressWarnings("all")
public class ImportsGenerator {
  private Inline_code imports;

  public ImportsGenerator(final Inline_code code) {
    this.imports = code;
  }

  public String generateImports() {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<String> _inline_code = this.imports.getInline_code();
      for(final String p : _inline_code) {
        String _replaceAll = p.replaceAll("^[$]", "").replaceAll("^[\\s]", "");
        _builder.append(_replaceAll);
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder.toString();
  }
}
