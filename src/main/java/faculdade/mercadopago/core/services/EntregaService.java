package faculdade.mercadopago.core.services;

import faculdade.mercadopago.core.applications.ports.ApiResponse;
import faculdade.mercadopago.core.domain.dto.EntregaDto;
import faculdade.mercadopago.core.domain.dto.ViewEntregaDto;
import faculdade.mercadopago.core.domain.mapper.EntregaMapper;
import faculdade.mercadopago.gateway.entity.EntregaEntity;
import faculdade.mercadopago.gateway.entity.PedidoEntity;
import faculdade.mercadopago.gateway.persistence.jpa.EntregaRepository;
import faculdade.mercadopago.gateway.persistence.jpa.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EntregaService {

    @Autowired
    private EntregaRepository entregaRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private EntregaMapper entregaMapper;

    @Autowired
    private PedidoService pedidoService;


    public ApiResponse<ViewEntregaDto> entregarPedido(EntregaDto entregaDto){
        PedidoEntity pedido = pedidoRepository.getReferenceById(entregaDto.getCodigo());
        pedido.setStatus(entregaDto.getStatus());
        var pedidoSalvo = pedidoRepository.save(pedido);

        EntregaEntity entrega = new EntregaEntity();
        entrega.setPedidoCodigo(pedidoSalvo);
        entrega.setDataHoraEntrega(LocalDateTime.now());
        var entregaSalva = entregaRepository.save(entrega);

        pedidoService.removerPedidoDaFila(entregaDto.getCodigo());

        var viewEntregaDto = entregaMapper.entityToDto(entregaSalva);

        var apiResponse = new ApiResponse<ViewEntregaDto>();
        apiResponse.setSuccess(true);
        apiResponse.setData(viewEntregaDto);
        return apiResponse;
    }
}
