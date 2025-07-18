package faculdade.mercadopago.exception;

public class EntityNotFoundException extends BusinessException {

    private static final long serialVersionUID = 1L;

    public EntityNotFoundException(Class<?> entityClass, Object id) {
        super(String.format("%s não encontrada para o parâmetro %s", entityClass.getSimpleName(), id));
    }

}