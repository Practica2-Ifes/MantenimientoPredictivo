package domainapp.modules.simple.dom.reportes;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class GeneradorDataSource implements JRDataSource{
private List<GeneradorReporte> listado=new ArrayList<GeneradorReporte>();
	
	private int indiceActual= -1;
	
	
	@Override
	public Object getFieldValue(JRField jrf) throws JRException {
		
		Object valor = null;
		if ("numeroDeSerie".equals(jrf.getName())){
			valor=listado.get(indiceActual).getNumeroDeSerie();
		}
		else if("consumoEnergetico".equals(jrf.getName())){
			
			valor=listado.get(indiceActual).getConsumoEnergetico();
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

	public void addGenerador(GeneradorReporte generadorReporte)  {
		// TODO Auto-generated method stub
		this.listado.add(generadorReporte);
	}

}
