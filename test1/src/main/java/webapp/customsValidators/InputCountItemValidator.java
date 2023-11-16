package webapp.customsValidators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("webapp.customsValidators.InputCountItemValidator")
public class InputCountItemValidator implements Validator {

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        try {
            int value = (int) o;
            if (!(value > 0 && value < 10000)) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ошибка", "Некорректное значение"));
            }
        } catch (NullPointerException e) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ошибка", "Некоректное значение"));
        }

    }
}
