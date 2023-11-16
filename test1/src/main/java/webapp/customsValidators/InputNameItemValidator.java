package webapp.customsValidators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.regex.Pattern;

/**
 * Данный класс отвечает за валидацию имени нового элемента на складе;
 *
 * Пусть к имени будут следующие требования:
 * Не содержит никаких символов кроме латиницы в любом регистре.
 *
 * Для работы применяется регулярное выражение.
 */
@FacesValidator("webapp.customsValidators.InputNameItemValidator")
public class InputNameItemValidator implements Validator{

    private static final String WORD = "^[A-Za-z]+$";

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        String value = (String) o;
        if (!value.matches(WORD)) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ошибка", "Некорректное имя элемента"));
        }
    }
}
