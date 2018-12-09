package domainapp.modules.simple.dom.reportes;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
import domainapp.modules.simple.generador.Generador;
import domainapp.modules.simple.generador.GeneradorRepository;
import domainapp.modules.simple.transformador.Transformador;
import domainapp.modules.simple.transformador.TransformadorRepository;
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
	
	@Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    @MemberOrder(sequence = "1")
	public Blob imprimirTecnico() throws JRException, IOException {
		
		TecnicoDataSource datasource = new TecnicoDataSource();
		
		for (Tecnico tec : tecnicoRepository.listarTecnico()){
		
			TecnicoReporte tecnicoReporte=new TecnicoReporte();
			tecnicoReporte.setName(tec.getName());
			tecnicoReporte.setApellido(tec.getApellido());
			tecnicoReporte.setEmail(tec.getEmail());
			tecnicoReporte.setTitulo(tec.getTitulo());
			tecnicoReporte.setMatricula(tec.getMatriculaProfesional());
			tecnicoReporte.setNumeroEmpleado(tec.getNumeroEmpleado());
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
	
	@SuppressWarnings("unchecked")
	@Action(semantics=SemanticsOf.SAFE)
	@ActionLayout(bookmarking=BookmarkPolicy.AS_ROOT)
	@MemberOrder(sequence="2")
	public Blob imprimirTecnicoIndividual(final Tecnico tecnico)throws JRException,IOException {
		
		List<Object> listaReporte = new ArrayList<Object>();
		TecnicoDataSource dataSource=new TecnicoDataSource();
		
		TecnicoReporte tecnicoReporte= new TecnicoReporte();
		tecnicoReporte.setName(tecnico.getName());
		tecnicoReporte.setApellido(tecnico.getApellido());
		tecnicoReporte.setEmail(tecnico.getEmail());
		tecnicoReporte.setNumeroEmpleado(tecnico.getNumeroEmpleado());
		tecnicoReporte.setTitulo(tecnico.getTitulo());
		tecnicoReporte.setMatricula(tecnico.getMatriculaProfesional());
		
		dataSource.addTecnico(tecnicoReporte); 
		listaReporte.add(tecnicoReporte);
		String jrxml="TecnicoSelec.jrxml";
		String nombreArchivo="Tecnico: "+tecnico.getApellido()+"_"+tecnico.getDocumento();
		
		return ReporteRepository.imprimirReporte(listaReporte, jrxml, nombreArchivo);
		
	}
	
	@Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    @MemberOrder(sequence = "3")
	public Blob imprimirGenerador() throws JRException, IOException {
		
		GeneradorDataSource datasource = new GeneradorDataSource();
		
		for (Generador gen : generadorRepository.listarGeneradores()){
		
			GeneradorReporte generadorReporte=new GeneradorReporte();
			generadorReporte.setEstadoUnidad(gen.getEstadoUnidad());
			generadorReporte.setDescripcion(gen.getDescripcion());
			generadorReporte.setConsumoEnergetico(gen.getConsumoEnergetico());
			datasource.addGenerador(generadorReporte);
		}
		String jrxml = "Generadores.jrxml";
		String nombreArchivo = "generadores";
		
		InputStream input = ReporteRepository.class.getResourceAsStream(jrxml);
		JasperDesign jd = JRXmlLoader.load(input);
		
		JasperReport reporte = JasperCompileManager.compileReport(jd);
		Map<String, Object> parametros = new HashMap<String, Object>();
		JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, datasource);
		
		return ReporteRepository.imprimirReporteLista(jasperPrint, jrxml, nombreArchivo);
		
	}
	@Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    @MemberOrder(sequence = "3")
	public Blob imprimirtransformadores() throws JRException, IOException {
		
		TransformadorDataSource datasource = new TransformadorDataSource();
		
		for (Transformador trf : transformadorRepository.listarTransformadores()){
		
			TransformadorReporte transformadorReporte=new TransformadorReporte();
			transformadorReporte.setEstadoUnidad(trf.getEstadoUnidad());
			transformadorReporte.setDescripcion(trf.getDescripcion());
			transformadorReporte.setVoltajeEntrada(trf.getVoltajeAnterior());
			transformadorReporte.setVoltajeSalida(trf.getVoltajeTransformado());
			datasource.addTransformador(transformadorReporte);
		}
		String jrxml = "Transformadores.jrxml";
		String nombreArchivo = "Tranformadores";
		
		InputStream input = ReporteRepository.class.getResourceAsStream(jrxml);
		JasperDesign jd = JRXmlLoader.load(input);
		
		JasperReport reporte = JasperCompileManager.compileReport(jd);
		Map<String, Object> parametros = new HashMap<String, Object>();
		JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, datasource);
		
		return ReporteRepository.imprimirReporteLista(jasperPrint, jrxml, nombreArchivo);
		
	}
	public List<Tecnico> choices0ImprimirTecnicoIndividual(){
		return tecnicoRepository.listarTecnico();
	}
	
	@Inject
	TecnicoRepository tecnicoRepository;
	
	@Inject
	GeneradorRepository generadorRepository;
	
	@Inject
	TransformadorRepository transformadorRepository;
}
