/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author leona
 */
public class ServletCalcularIMC extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public static String encodeURIcomponent(String s)
    {
        StringBuilder o = new StringBuilder();
        for (char ch : s.toCharArray()) {
            if (isUnsafe(ch)) {
                o.append('%');
                o.append(toHex(ch / 16));
                o.append(toHex(ch % 16));
            }
            else o.append(ch);
        }
        return o.toString();
    }

    private static char toHex(int ch)
    {
        return (char)(ch < 10 ? '0' + ch : 'A' + ch - 10);
    }

    private static boolean isUnsafe(char ch)
    {
        if (ch > 128 || ch < 0)
            return true;
        return " %$&+,/:;=?@<>#%".indexOf(ch) >= 0;
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String sexo = request.getParameter("sexo");
            String peso = request.getParameter("peso");
            String altura = request.getParameter("altura");
            if (!altura.equals("") && !peso.equals("") && !sexo.equals("")){
                altura = altura.replace(",", ".");
                peso = peso.replace(",", ".");
                double imc = Double.parseDouble(peso) / (Double.parseDouble(altura) * Double.parseDouble(altura));
                String message, color;
                if (sexo.equals("masculino")){
                    if (imc < 20.7){
                        message = "<b>Cuidado!</b> Você está abaixo do peso.";
                        color = "red";
                    }
                    else if (imc < 26.4){
                        message = "<b>Otimo!</b> Você no peso normal.";
                        color = "green";
                    }
                    else if (imc < 27.8){
                        message = "<b>Atenção!</b> Você marginalmente acima do peso.";
                        color = "yellow darken-2";
                    }
                    else if (imc < 31.1){
                        message = "<b>Cuidado!</b> Você acima do peso ideal.";
                        color = "orange";
                    }
                    else {
                        message = "<b>Cuidado!</b> Você na faixa de obesidade.";
                        color = "red";
                    }
                }
                else{
                    if (imc < 19.1){
                        message = "<b>Cuidado!</b> Você abaixo do peso.";
                        color = "red";
                    }
                    else if (imc < 25.8){
                        message = "<b>Otimo!</b> Você no peso normal.";
                        color = "green";
                    }
                    else if (imc < 27.3){
                        message = "<b>Atenção!</b> Você marginalmente acima do peso.";
                        color = "yellow darken-2";
                    }
                    else if (imc < 32.3){
                        message = "<b>Cuidado!</b> Você acima do peso ideal.";
                        color = "orange";
                    }
                    else {
                        message = "<b>Cuidado!</b> Você na faixa de obesidade.";
                        color = "red";
                    }
                }
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp?imc=" + encodeURIcomponent("" + imc) + "&message=" + encodeURIcomponent(message) + "&cardColor=" + encodeURIcomponent(color));
                dispatcher.forward(request,response);
            }
            else{
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp?error=" + encodeURIcomponent("<b>Erro:</b> Informe corretamente os dados acima."));
                dispatcher.forward(request,response);
            }
        }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
