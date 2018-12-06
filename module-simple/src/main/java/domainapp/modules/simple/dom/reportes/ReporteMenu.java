package domainapp.modules.simple.dom.reportes;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.value.Blob;


import domainapp.modules.simple.dom.tecnico.Tecnico;
import domainapp.modules.simple.dom.tecnico.TecnicoRepository;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

@DomainService(nature=NatureOfService.VIEW_MENU_ONLY, objectType="simple.ReporteMenu")
@DomainServiceLayout(named="reportes")
public class ReporteMenu {
	
//	@Action(semantics=SemanticsOf.SAFE)
//	@ActionLayout(bookmarking=BookmarkPolicy.AS_ROOT)
//	public Blob ImprimirTecnico(@ParameterLayout(named="Tecnico")final Tecnico tecnicoSelec) throws JRException,IOException{
//		List<Object> reporte=new ArrayList<Object>();
//		
//		
//		TecnicoReporte tecnicos = new TecnicoReporte();
//		tecnicos.setName(tecnicoSelec.getName());
////		tecnicoReporte.setTitulo(tecnicoSelec.getTitulo());
////		tecnicoReporte.setEmail(tecnicoSelec.getEmail());
////		tecnicoReporte.setNumeroEmpleado(tecnicoSelec.getNumeroEmpleado());
//		reporte.add(tecnicos);
//		String jasperxml="Tecnicos.jrxml";
//		String nombreArchivo="Tecnico "+tecnicoSelec.getName();
//		return ReporteRepository.imprimirReporte(reporte);
//	}
	
	
//	public List<Tecnico> choices0ImprimirTecnico(){
//		return tecnicoRepository.listarTecnico();
//	}
	
	@Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    @MemberOrder(sequence = "1")
	public Blob imprimirTecnico() throws JRException, IOException {
		
		TecnicoDataSource datasource = new TecnicoDataSource();
		
		for (Tecnico tec : tecnicoRepository.listarTecnico()){
		
			TecnicoReporte tecnicoReporte=new TecnicoReporte();
			tecnicoReporte.setName(tec.getName());
			datasource.addTecnico(tecnicoReporte);
		}
		String jrxml = "Tecnicos.jrxml";
		String nombreArchivo = "Listado Tecnico ";
		
		InputStream input = ReporteRepository.class.getResourceAsStream(jrxml);
		JasperDesign jd = JRXmlLoader.load(input);
		
		JasperReport reporte = JasperCompileManager.compileReport(jd);
		Map<String, Object> parametros = new HashMap<String, Object>();
		JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, datasource);
		
		return ReporteRepository.imprimirReporteLista(jasperPrint, jrxml, nombreArchivo);
		
	}
	
	@Inject
	TecnicoRepository tecnicoRepository;
}
