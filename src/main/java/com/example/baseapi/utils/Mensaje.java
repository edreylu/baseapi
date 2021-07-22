package com.example.baseapi.utils;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Admin
 */
public class Mensaje {
    private String mensaje;
    private int result;

    public Mensaje(String mensaje, int result) {
        this.mensaje = mensaje;
        this.result = result;
    }

    public Mensaje() {
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public RedirectAttributes mensaje(String mensaje, String clase, RedirectAttributes redirectAttrs) {
        return redirectAttrs.addFlashAttribute("mensaje", mensaje).addFlashAttribute("clase", clase);
    }

    public RedirectAttributes crearMensaje(Mensaje msg, RedirectAttributes redirectAttrs) {
        String[] tipoMensajes = {"info", "success", "danger", "warning",
                "primary", "secundary", "light", "dark"};
        String clase = tipoMensajes[msg.getResult()];
        return mensaje(msg.getMensaje(), clase, redirectAttrs);
    }

    public static Mensaje CREATE(String mensaje, int result){
        return new Mensaje(mensaje,result);
    }

}
