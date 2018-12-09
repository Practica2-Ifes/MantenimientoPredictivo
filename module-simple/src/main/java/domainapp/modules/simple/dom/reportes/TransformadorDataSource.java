package domainapp.modules.simple.dom.reportes;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class TransformadorDataSource implements JRDataSource{
private List<TransformadorReporte> listado=new ArrayList<TransformadorReporte>();
	
	private int indiceActual= -1;
	
	
	@Override
	public Object getFieldValue(JRField jrf) throws JRException {
		
		Object valor = null;
		
		if("estadoUnidad".equals(jrf.getName())){
			valor=listado.get(indiceActual).getEstadoUnidad();
		}
		else if("descripcion".equals(jrf.getName())) {
			valor=listado.get(indiceActual).getDescripcion();
		}
		else if("voltajeEntrada".equals(jrf.getName())) {
			valor=listado.get(indiceActual).getVoltajeEntrada();
		}
		else if("voltajeSalida".equals(jrf.getName())) {
			valor=listado.get(indiceActual).getVoltajeSalida();
		}
		return valor;
	}
	
	@Override
	public boolean next() throws JRException {
		return ++indiceActual<listado.size();
	}

	public void addTransformador(TransformadorReporte transformadorReporte)  {
		// TODO Auto-generated method stub
		this.listado.add(transformadorReporte);
	}
}
