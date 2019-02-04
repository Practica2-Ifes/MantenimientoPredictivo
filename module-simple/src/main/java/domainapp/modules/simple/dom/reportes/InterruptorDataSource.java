package domainapp.modules.simple.dom.reportes;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class InterruptorDataSource implements JRDataSource{
	
private List<InterruptorReporte> listado=new ArrayList<InterruptorReporte>();
	
	private int indiceActual= -1;
	
	
	@Override
	public Object getFieldValue(JRField jrf) throws JRException {
		
		Object valor = null;
		if ("numeroDeSerie".equals(jrf.getName())){
			valor=listado.get(indiceActual).getNumeroDeSerie();
		}
		else if("amperajeSoportado".equals(jrf.getName())){
			
			valor=listado.get(indiceActual).getAmperajeSoportado();
		}
		else if("descripcion".equals(jrf.getName())) {
			valor=listado.get(indiceActual).getDescripcion();
		}
		else if("estadoUnidad".equals(jrf.getName())) {
			valor=listado.get(indiceActual).getEstadoUnidad();
		}
		return valor;
	}
	
	@Override
	public boolean next() throws JRException {
		return ++indiceActual<listado.size();
	}

	public void addInterruptor(InterruptorReporte interruptorReporte)  {
		// TODO Auto-generated method stub
		this.listado.add(interruptorReporte);
	}
}
