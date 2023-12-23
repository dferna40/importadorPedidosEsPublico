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
 * Controlador para manejar las solicitudes relacionadas con el historial de pedidos.
 *
 * Esta clase controladora se encarga de la gestión de vistas y datos asociados al historial de pedidos
 * importados. Utiliza el servicio 'historyOrderService' para recuperar y mostrar la información de historial.
 */
@Controller
public class HistoryOrderController {
	
	private static final Logger logger = LoggerFactory.getLogger(HistoryOrderController.class);

	@Autowired
    private HistoryOrderServiceImpl historyOrderService;

	/**
     * Maneja la solicitud GET para mostrar la vista del historial de pedidos.
     *
     * Este método gestiona las solicitudes a la ruta "/history" y se encarga de preparar y mostrar 
     * la vista asociada al historial de pedidos. Recupera todos los registros de historial mediante
     * el servicio 'historyOrderService' y los pasa a la vista para su visualización.
     *
     * La función obtiene la lista de todos los historiales de pedidos y los agrega al modelo ModelAndView.
     * Luego establece el nombre de la vista que debe renderizarse y retorna el nombre de esta vista.
     * Cualquier error durante la obtención de los historiales se maneja internamente y se registra 
     * adecuadamente mediante el logger.
     * 
     * @param mav Objeto ModelAndView utilizado para agregar datos al modelo y configurar la vista.
     * @return String Retorna el nombre de la vista a renderizar, en este caso, "historyView".
     */
    @GetMapping("/history")
    public String showHistory(ModelAndView mav) {
        List<HistoryOrder> historyOrders = historyOrderService.getAllHistoryOrders();
        mav.addObject("historyOrders", historyOrders);
        mav.setViewName("index");
        return "historyView";
    }
}
