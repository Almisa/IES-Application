package com.mycompany.mqtttest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.paho.client.mqttv3.MqttException;

/**
 *
 * @author joseppmoreira
 */
public class MyServlet extends HttpServlet {
    Subscriber sub;
    String string, string2;
    boolean alert1, alert2;
    int number1, number2;
    List <Double> x1, y1, z1, x2, y2, z2;
    
    public MyServlet() throws MqttException, URISyntaxException {
        this.sub = new Subscriber("tcp://m23.cloudmqtt.com:15660", this);
        //this.sub = new Subscriber("tcp://m14.cloudmqtt.com:12415", this);
        this.string = " / / ";
        this.string2 = " / / ";
        this.selectMaria("1");
        this.selectMaria("2");
        this.x1 = new ArrayList<>();
        this.y1 = new ArrayList<>();
        this.z1 = new ArrayList<>();
        this.x2 = new ArrayList<>();
        this.y2 = new ArrayList<>();
        this.z2 = new ArrayList<>();
    }
    
    public void setString(String string){
        this.string = string;
    }
   
    public void setString2(String string2){
        this.string2 = string2;
    }
    
    public void setAlert(boolean alert, int number){
        if(number == 1){
            this.alert1 = alert;
            this.number1 = number;
        }
        else if (number == 2){
            this.alert2 = alert;
            this.number2 = number;
        }
    }
    
    public void selectMaria(String id){
        Maria_Select db = new Maria_Select();
        if("1".equals(id)){
            this.x1 = db.selectX(Double.parseDouble(id));
            this.y1 = db.selectY(Double.parseDouble(id));
            this.z1 = db.selectZ(Double.parseDouble(id));
        }
        if("2".equals(id)){
            this.x2 = db.selectX(Double.parseDouble(id));
            this.y2 = db.selectY(Double.parseDouble(id));
            this.z2 = db.selectZ(Double.parseDouble(id));
        }
        
    }
    
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MyServlet</title>");
            out.println("<meta http-equiv=\"refresh\" content=\"10\" />");
           
            out.println("<script>\n" +
"window.onload = function () {\n" +
"\n" +
"var chart1 = new CanvasJS.Chart(\"chartContainer1\", {\n" +
"	animationEnabled: true,\n" +
"	theme: \"light2\",\n" +
"	title:{\n" +
"		text: \"Idoso 1\"\n" +
"	},\n" +
"	axisY:{\n" +
"		includeZero: false\n" +
"	},\n" +
"	data: [{        \n" +
"		type: \"line\",       \n" +
"		dataPoints: [");
                    for(int i = 0; i<x1.size(); i++){
                        out.println("{ y: "+ x1.get(i) +" },");
                    }
		out.println("]\n" +
"	},{        \n" +
"		type: \"line\",       \n" +
"		dataPoints: [");
                    for(int i = 0; i<y1.size(); i++){
                        out.println("{ y: "+ y1.get(i) +" },");
                    }
		out.println("]\n" +
"	},{        \n" +
"		type: \"line\",       \n" +
"		dataPoints: [");
                    for(int i = 0; i<z1.size(); i++){
                        out.println("{ y: "+ z1.get(i) +" },");
                    }
		out.println("]\n" +
"	}]\n" +
"});\n" +
"chart1.render();\n" +
"\n" +
"var chart2 = new CanvasJS.Chart(\"chartContainer2\", {\n" +
"	animationEnabled: true,\n" +
"	theme: \"light2\",\n" +
"	title:{\n" +
"		text: \"Idoso 2\"\n" +
"	},\n" +
"	axisY:{\n" +
"		includeZero: false\n" +
"	},\n" +
"	data: [{        \n" +
"		type: \"line\",       \n" +
"		dataPoints: [");
                    for(int i = 0; i<x2.size(); i++){
                        out.println("{ y: "+ x2.get(i) +" },");
                    }
		out.println("]\n" +
"	},{        \n" +
"		type: \"line\",       \n" +
"		dataPoints: [");
                    for(int i = 0; i<y2.size(); i++){
                        out.println("{ y: "+ y2.get(i) +" },");
                    }
		out.println("]\n" +
"	},{        \n" +
"		type: \"line\",       \n" +
"		dataPoints: [");
                    for(int i = 0; i<z2.size(); i++){
                        out.println("{ y: "+ z2.get(i) +" },");
                    }
		out.println("]\n" +
"	}]\n" +
"});\n" +
"chart2.render();\n" +
"\n" +
"}\n" +
"</script>");
            
            out.println("</head>");
            out.println("<body>");
            
            out.println("<h1 align=\"center\">TheVet a.k.a. The Elder's Savior</h1>");
            
            out.println("<div align=\"center\" style=\"width: 100%; overflow: hidden;\">");
            out.println("<div style=\"width: 50%; float: left;\">");
            out.println("Idoso 1");
            out.println("<form action=\"/action_page.php\">");
            out.println("x: <input readonly type=\"text\" name=\"x\" value="+string.split("/")[0]+"><br>");
            out.println("y: <input readonly type=\"text\" name=\"y\" value="+string.split("/")[1]+"><br>");
            out.println("z: <input readonly type=\"text\" name=\"z\" value="+string.split("/")[2]+"><br>");
            out.println("</form> ");
            out.println("</div>");            
            
            out.println("<div style=\"margin-left: 50%;\">");
            out.println("Idoso 2");
            out.println("<form action=\"/action_page.php\">");
            out.println("x: <input readonly type=\"text\" name=\"x\" value="+string2.split("/")[0]+"><br>");
            out.println("y: <input readonly type=\"text\" name=\"y\" value="+string2.split("/")[1]+"><br>");
            out.println("z: <input readonly type=\"text\" name=\"z\" value="+string2.split("/")[2]+"><br>");
            out.println("</form> ");
            out.println("</div>");
            out.println("</div>");
                    
            if (alert1 || alert2){
                if(alert1 && alert2){
                    out.println("<script>\n" + "alert(\"Alerta! Idosos "+ number1 + " e " + number2 +" necessitam de ajuda!\");\n" + "</script>");
                }
                else if(alert1){
                    out.println("<script>\n" + "alert(\"Alerta! Idoso "+ number1 +" necessita de ajuda!\");\n" + "</script>");
                }
                else if(alert2){
                    out.println("<script>\n" + "alert(\"Alerta! Idoso "+ number2 +" necessita de ajuda!\");\n" + "</script>");
                }
            }
            
            out.println("<div style=\"height: 100px;\"></div>");
            out.println("<div id=\"chartContainer1\" style=\"height: 300px; width: 100%;\"></div>\n" +
                        "<script src=\"https://canvasjs.com/assets/script/canvasjs.min.js\"></script>");
            out.println("<div style=\"height: 100px;\"></div>");
            out.println("<div id=\"chartContainer2\" style=\"height: 300px; width: 100%;\"></div>\n" +
                        "<script src=\"https://canvasjs.com/assets/script/canvasjs.min.js\"></script>");
            
            
            out.println("</body>");
            out.println("</html>");
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
