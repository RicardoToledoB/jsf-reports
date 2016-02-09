package com.proyecto.bean;

import com.proyecto.model.Usuario;
import com.proyecto.service.UsuarioServiceImpl;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;


import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRPptxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;

/**
 *
 * @author ricardotoledo
 */
@Named(value = "usuarioBean")
@SessionScoped
public class UsuarioBean implements Serializable {

    @Inject
    private UsuarioServiceImpl uService;
    private List<Usuario> lista;
    JasperPrint jasperPrint;

    public UsuarioBean() {
    }

    public List<Usuario> getLista() {
        lista = uService.list();
        return lista;
    }

    public void setLista(List<Usuario> lista) {
        this.lista = lista;
    }

    public void init() throws JRException {
        //JRDataSource beanDataSource = new JRBeanCollectionDataSource(lista);
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("reportes/report3.jasper");
//        jasperPrint = JasperFillManager.fillReport( reportPath ,new HashMap() , beanDataSource );
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(lista, false);
        jasperPrint = JasperFillManager.fillReport(reportPath, new HashMap(), beanCollectionDataSource);
    }

    public void PDF() throws JRException, IOException {
        init();
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=report4.pdf");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
        FacesContext.getCurrentInstance().responseComplete();
    }
    public void EXCEL() throws JRException, IOException {
        init();
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=report4.xls");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JRXlsxExporter exporter=new JRXlsxExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
        exporter.exportReport();
        servletOutputStream.flush();
        servletOutputStream.close();
        FacesContext.getCurrentInstance().responseComplete();
        
    }
    public void PPT() throws JRException, IOException {
        init();
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=report4.ppt");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JRPptxExporter exporter=new JRPptxExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
        exporter.exportReport();
        servletOutputStream.flush();
        servletOutputStream.close();
        FacesContext.getCurrentInstance().responseComplete();
        
    }
     public void DOC() throws JRException, IOException {
        init();
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=report4.doc");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JRDocxExporter exporter=new JRDocxExporter ();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
        exporter.exportReport();
        servletOutputStream.flush();
        servletOutputStream.close();
        FacesContext.getCurrentInstance().responseComplete();
        
    }

}
