package domainapp.modules.simple.dom.reportes;

import java.util.ArrayList;
import java.util.List;


import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class TecnicoDataSource implements JRDataSource{
	
	private List<TecnicoReporte> listado=new ArrayList<TecnicoReporte>();
	
	private int indiceActual= -1;
	
	
	@Override
	public Object getFieldValue(JRField jrf) throws JRException {
		
		Object valor = null;
		
		if("name".equals(jrf.getName())){
			
			valor=listado.get(indiceActual).getName();
		}
		else if("apellido".equals(jrf.getName())) {
			valor=listado.get(indiceActual).getApellido();
		}
		else if("email".equals(jrf.getName())) {
			valor=listado.get(indiceActual).getEmail();
		}
		else if("numeroEmpleado".equals(jrf.getName())) {
			valor=listado.get(indiceActual).getNumeroEmpleado();
		}
		else if("titulo".equals(jrf.getName())) {
			valor=listado.get(indiceActual).getTitulo();
		}
		else if("matricula".equals(jrf.getName())) {
			valor=listado.get(indiceActual).getMatricula();
		}
		return valor;
	}
	
	@Override
	public boolean next() throws JRException {
		return ++indiceActual<listado.size();
	}

	public void addTecnico(TecnicoReporte tecnicoReporte)  {
		// TODO Auto-generated method stub
		this.listado.add(tecnicoReporte);
	}

}
