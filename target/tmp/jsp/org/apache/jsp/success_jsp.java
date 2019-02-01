package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class success_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_shiro_principal_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_shiro_hasAnyRoles_name;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_shiro_principal_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_shiro_hasAnyRoles_name = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_shiro_principal_nobody.release();
    _jspx_tagPool_shiro_hasAnyRoles_name.release();
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("    <title></title>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("     ");
      if (_jspx_meth_shiro_hasAnyRoles_0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("</body>\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_shiro_hasAnyRoles_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  shiro:hasAnyRoles
    org.apache.shiro.web.tags.HasAnyRolesTag _jspx_th_shiro_hasAnyRoles_0 = (org.apache.shiro.web.tags.HasAnyRolesTag) _jspx_tagPool_shiro_hasAnyRoles_name.get(org.apache.shiro.web.tags.HasAnyRolesTag.class);
    _jspx_th_shiro_hasAnyRoles_0.setPageContext(_jspx_page_context);
    _jspx_th_shiro_hasAnyRoles_0.setParent(null);
    _jspx_th_shiro_hasAnyRoles_0.setName("admin");
    int _jspx_eval_shiro_hasAnyRoles_0 = _jspx_th_shiro_hasAnyRoles_0.doStartTag();
    if (_jspx_eval_shiro_hasAnyRoles_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("         ");
        if (_jspx_meth_shiro_principal_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_shiro_hasAnyRoles_0, _jspx_page_context))
          return true;
        out.write("拥有角色admin\n");
        out.write("     ");
        int evalDoAfterBody = _jspx_th_shiro_hasAnyRoles_0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_shiro_hasAnyRoles_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_shiro_hasAnyRoles_name.reuse(_jspx_th_shiro_hasAnyRoles_0);
      return true;
    }
    _jspx_tagPool_shiro_hasAnyRoles_name.reuse(_jspx_th_shiro_hasAnyRoles_0);
    return false;
  }

  private boolean _jspx_meth_shiro_principal_0(javax.servlet.jsp.tagext.JspTag _jspx_th_shiro_hasAnyRoles_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  shiro:principal
    org.apache.shiro.web.tags.PrincipalTag _jspx_th_shiro_principal_0 = (org.apache.shiro.web.tags.PrincipalTag) _jspx_tagPool_shiro_principal_nobody.get(org.apache.shiro.web.tags.PrincipalTag.class);
    _jspx_th_shiro_principal_0.setPageContext(_jspx_page_context);
    _jspx_th_shiro_principal_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_shiro_hasAnyRoles_0);
    int _jspx_eval_shiro_principal_0 = _jspx_th_shiro_principal_0.doStartTag();
    if (_jspx_th_shiro_principal_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_shiro_principal_nobody.reuse(_jspx_th_shiro_principal_0);
      return true;
    }
    _jspx_tagPool_shiro_principal_nobody.reuse(_jspx_th_shiro_principal_0);
    return false;
  }
}
