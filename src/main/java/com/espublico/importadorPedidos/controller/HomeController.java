package com.espublico.importadorPedidos.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.espublico.importadorPedidos.model.HistoryOrder;
import com.espublico.importadorPedidos.service.impl.HistoryOrderServiceImpl;

/**
 * Controlador Spring MVC que maneja las rutas web para la aplicación de importación de pedidos.
 *
 * Esta clase contiene métodos que responden a las solicitudes HTTP GET para diferentes rutas,
 * proporcionando la lógica necesaria para mostrar las vistas adecuadas y pasar los datos
 * necesarios a estas vistas.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
    private HistoryOrderServiceImpl historyOrderService;

	/**
	 * Maneja la solicitud GET para mostrar la página principal con un historial de pedidos importados.
	 *
	 * Este método gestiona las solicitudes a la ruta raíz ("/") o a "/inicio" y se encarga
	 * de preparar y mostrar la página principal con un listado del historial de pedidos
	 * importados. Utiliza el servicio 'historyOrderService' para obtener todos los registros
	 * de historial y los pasa a la vista para su visualización.
	 *
	 * @param mav Un objeto ModelAndView que se utiliza para definir el modelo y la vista.
	 * @return ModelAndView El objeto ModelAndView con los datos del historial y la vista
	 *         configurada para ser renderizada.
	 */
	@GetMapping({"/", "/inicio"})
    public ModelAndView showHistoryOrder(ModelAndView mav) {
        List<HistoryOrder> historyOrders = historyOrderService.getAllHistoryOrders();
        mav.addObject("historyOrders", historyOrders);
        mav.setViewName("index");
        return mav;
    }
	

	@GetMapping("/importar")
	public String finalSummary() {
		return "finalSummary";
	}

}
