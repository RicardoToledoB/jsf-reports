/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

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
        lista=uService.list();
        return lista;
    }

    public void setLista(List<Usuario> lista) {
        this.lista = lista;
    }

    public void init() throws JRException {
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(lista,false);
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("reportes/report3.jasper");
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

}
