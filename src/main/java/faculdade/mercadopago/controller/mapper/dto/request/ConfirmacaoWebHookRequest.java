package faculdade.mercadopago.controller.mapper.dto.request;

public record ConfirmacaoWebHookRequest(
        String id,
        Boolean live_mode,
        String type,
        String date_created,
        Long user_id,
        Long api_version,
        String action,
        ConfirmacaoWebHookRequest.PaymentData data
) {
    record PaymentData(
            Long id
    ){}
}