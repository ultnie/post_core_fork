package su.nsk.iae.post.generator.py.common.vars;

import org.eclipse.emf.ecore.EObject;
import su.nsk.iae.post.poST.OutputVarDeclaration;

@SuppressWarnings("all")
public class OutputVarHelper extends VarHelper {
  public OutputVarHelper() {
    this.varType = "VAR_OUTPUT";
  }

  @Override
  public void add(final EObject varDecl, final String pref) {
    if ((varDecl instanceof OutputVarDeclaration)) {
      this.parseSimpleVar(((OutputVarDeclaration)varDecl).getVars(), pref);
    }
  }
}
