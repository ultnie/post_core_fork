package su.nsk.iae.post.generator.py.common.vars;

import org.eclipse.emf.ecore.EObject;
import su.nsk.iae.post.poST.InputVarDeclaration;

@SuppressWarnings("all")
public class InputVarHelper extends VarHelper {
  public InputVarHelper() {
    this.varType = "VAR_INPUT";
  }

  @Override
  public void add(final EObject varDecl, final String pref) {
    if ((varDecl instanceof InputVarDeclaration)) {
      this.parseSimpleVar(((InputVarDeclaration)varDecl).getVars(), pref);
    }
  }
}
