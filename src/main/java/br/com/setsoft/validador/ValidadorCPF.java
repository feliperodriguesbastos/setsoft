package br.com.setsoft.validador;

import java.util.Arrays;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import br.com.setsoft.utilidade.StringUtil;

@FacesValidator(value = "validadorCPF")
public class ValidadorCPF implements Validator {
	
	@Override
    public void validate(FacesContext arg0, UIComponent arg1, Object valorTela) throws ValidatorException {
		
		if (StringUtil.isPreenchida(String.valueOf(valorTela)) && !validaCPF(String.valueOf(valorTela))) {
              FacesMessage message = new FacesMessage();
              message.setSeverity(FacesMessage.SEVERITY_ERROR);
              message.setSummary(ResourceBundle.getBundle(FacesContext.getCurrentInstance().getApplication().getMessageBundle()).getString("erro.validacao.cpf"));
              throw new ValidatorException(message);
		}
    }
	
	 /**
     * Valida CPF do usuário. Não aceita CPF's padrões como
     * 11111111111 ou 22222222222
     *
     * @param cpf String valor com 11 dígitos
     */
     private static boolean validaCPF(String cpf) {
          if (cpf == null || cpf.length() != 11 || isCPFPadrao(cpf))
               return false;
 
          try {
               Long.parseLong(cpf);
          } catch (NumberFormatException e) { // CPF não possui somente números
           return false;
          }
 
      return calcDigVerif(cpf.substring(0, 9)).equals(cpf.substring(9, 11));
     }
 
     /**
     *
     * @param cpf String valor a ser testado
     * @return boolean indicando se o usuário entrou com um CPF padrão
     */
     private static boolean isCPFPadrao(String cpf) {
      
      return Arrays.asList("00000000000", "11111111111", "22222222222", "33333333333", "44444444444", "55555555555",
    		  "66666666666", "77777777777", "88888888888", "99999999999").contains(cpf);
     }
 
     private static String calcDigVerif(String num) {
          Integer primDig, segDig;
      int soma = 0, peso = 10;
      for (int i = 0; i < num.length(); i++)
           soma += Integer.parseInt(num.substring(i, i + 1)) * peso--;
 
      if (soma % 11 == 0 | soma % 11 == 1)
           primDig = new Integer(0);
          else
               primDig = new Integer(11 - (soma % 11));
 
      soma = 0;
          peso = 11;
          for (int i = 0; i < num.length(); i++)
               soma += Integer.parseInt(num.substring(i, i + 1)) * peso--;
 
          soma += primDig.intValue() * 2;
          if (soma % 11 == 0 | soma % 11 == 1)
               segDig = new Integer(0);
          else
               segDig = new Integer(11 - (soma % 11));
 
          return primDig.toString() + segDig.toString();
     }
}