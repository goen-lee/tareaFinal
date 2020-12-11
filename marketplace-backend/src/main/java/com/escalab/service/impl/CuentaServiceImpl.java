package com.escalab.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.escalab.dto.CuentaListaProductoDTO;
import com.escalab.dto.CuentaResumenDTO;
import com.escalab.dto.FiltroCuentaDTO;
import com.escalab.model.Cuenta;
import com.escalab.repo.ICompraRepo;
import com.escalab.repo.ICuentaRepo;
import com.escalab.repo.IVendeRepo;
import com.escalab.service.ICuentaService;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class CuentaServiceImpl  implements ICuentaService {

	@Autowired
	private ICuentaRepo repo;
	
	@Autowired
	private ICompraRepo cpRepo;
	
	private IVendeRepo vtRepo;
		
	@Override
	public Cuenta registrar(Cuenta obj) {
		return repo.save(obj);
	}	
	
	@Transactional
	@Override
	public Cuenta registrarTransaccionalCompra(CuentaListaProductoDTO dto) {
		repo.save(dto.getCuenta());
		dto.getLstProducto().forEach(pr -> cpRepo.registrar(dto.getCuenta().getIdCuenta(), pr.getIdProducto()));
		return dto.getCuenta();
	}
	
	@Transactional
	@Override
	public Cuenta registrarTransaccionalVende(CuentaListaProductoDTO dto) {
		repo.save(dto.getCuenta());
		dto.getLstProducto().forEach(pr -> vtRepo.registrar(dto.getCuenta().getIdCuenta(), pr.getIdProducto()));
		return dto.getCuenta();
	}
	
	@Override
	public Cuenta modificar(Cuenta obj) {
		return repo.save(obj);
	}
		
	@Override
	public List<Cuenta> listar(){
		return repo.findAll();
	}
		
	@Override
	public Page<Cuenta> listarPageable(Pageable pageable) {
		return repo.findAll(pageable);
	}
	
	@Override
	public Cuenta leerPorId(Integer id) {
		Optional<Cuenta> op =  repo.findById(id);
		return op.isPresent() ? op.get() : new Cuenta();
	}
		
	@Override
	public boolean eliminar(Integer id) {
		repo.deleteById(id);
		return true;
	}
	
	@Override
	public List<Cuenta> buscar(FiltroCuentaDTO filtro) {		
		return repo.buscar(filtro.getNombreCompleto(), filtro.getNombreUsuario());
	}
	
	@Override
	public List<Cuenta> buscarTarjetaCredito(FiltroCuentaDTO filtro) {
		return repo.buscarTarjetaCredito(filtro.getTarjetaCredito());
	}
	
	@Override
	public List<CuentaResumenDTO> listarResumen() {
		List<CuentaResumenDTO> cuenta = new ArrayList<>();
		
		repo.listarResumen().forEach(x -> {
			CuentaResumenDTO cr = new CuentaResumenDTO();
			cr.setCantidad(Integer.parseInt(String.valueOf(x[0])));
			cr.setFecha(String.valueOf(x[1]));
			cuenta.add(cr);
		});
		return cuenta;
	}
	
	@Override
	public byte[] generarReporte() {
		byte[] data = null;
		
		try {
			File file = new ClassPathResource("/reports/consultas.jasper").getFile();
			JasperPrint print = JasperFillManager.fillReport(file.getPath(), null, new JRBeanCollectionDataSource(this.listarResumen()));
			data = JasperExportManager.exportReportToPdf(print);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;	
	}
	
}
