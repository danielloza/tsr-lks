package com.confia.springboot.service.tsr.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.confia.springboot.service.tsr.dao.ICatBancosDAO;
import com.confia.springboot.service.tsr.dao.ICatCuentasBancosDAO;
import com.confia.springboot.service.tsr.dao.ICatParamExBancosDAO;
import com.confia.springboot.service.tsr.models.CatBancos;
import com.confia.springboot.service.tsr.models.CatCuentasBancos;
import com.confia.springboot.service.tsr.models.CatParamExeBancos;

@Service
public class CatBancosServiceImpl implements ICatBancosService{

	@Autowired
	public ICatBancosDAO cBancosDao;
	
	@Autowired
	public ICatCuentasBancosDAO ccBancosDao;
	
	@Autowired
	public ICatParamExBancosDAO cpebDao;
	
	@Override
	@Transactional
	public CatBancos addBancos(CatBancos cbancos) {
		
		CatBancos banco = new CatBancos();
		CatBancos banco2 = new CatBancos();
		CatCuentasBancos cpeBancos2 = new CatCuentasBancos();
		
		/*Obtenemos los datos del banco del objeto enviado desde la api*/
		banco.setNombre(cbancos.getNombre());
		banco.setActivo(cbancos.isActivo());
		
		/*Guardamos el objeto Bancos*/
		banco2 = cBancosDao.save(banco);
		
		/*Recorremos la lista de cuentas bancos que se mandan por el JSON 
		  y se guardan en el Objeto CatCuentasBancos 
		  para enlazar las cuentas con el banco*/
		for(CatCuentasBancos ccBancos : cbancos.getCatCuentasBancos()) {
			
			/*Seteamos el objeto CatCuentasBancos agregada anteriormente para obtener el id del banco 	*/
			ccBancos.setBanco(banco2);
			
			cpeBancos2 =  ccBancosDao.save(ccBancos);
			
			for(CatParamExeBancos cpeBancos : cbancos.getParamBancos()) {
				
				cpeBancos.setCatalogoBanco(banco2);
				
				cpeBancos.setCuentaBanco(cpeBancos2);
				
				cpeBancos.setFechaRegistro(new Date());
				
				cpebDao.save(cpeBancos);
			}
			
		}
		
		return cBancosDao.findById(banco2.getBancoID()).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CatBancos> findAllBancos() {
		
		return cBancosDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public CatBancos findById(Integer bancoID) {
		
		return cBancosDao.findById(bancoID).orElse(null);
	}

	@Override
	@Transactional
	public CatBancos update(CatBancos cbancos) {
		
		CatBancos banco = null;
		CatCuentasBancos cuentaBanco = null;
		CatParamExeBancos param = null;
		
		/*for(CatCuentasBancos ccBancos : cbancos.getCatCuentasBancos()) {
			
			cuentaBanco = ccBancosDao.find(ccBancos.getNumeroCuenta());
			
			//Seteamos el objeto CatCuentasBancos agregada anteriormente para obtener el id del banco 	
			ccBancos.setBanco(banco2);
			
			cpeBancos2 =  ccBancosDao.save(ccBancos);
			
			for(CatParamExeBancos cpeBancos : cbancos.getParamBancos()) {
				
				cpeBancos.setCatalogoBanco(banco2);
				
				cpeBancos.setCuentaBanco(cpeBancos2);
				
				cpeBancos.setFechaRegistro(new Date());
				
				cpebDao.save(cpeBancos);
			}
			
		}*/
		
		
		banco = cBancosDao.save(cbancos);
		
		/*Regresamos el objeto actualizado*/
		return cBancosDao.findById(banco.getBancoID()).orElse(null);
		
	}

}
