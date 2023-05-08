package com.gym1.util;


public class EmailUtil {

    public static String orderEmail(String year, String month, String day, int id, String name, String phone,
                                    String username, String venueName, String address, String begin,
                                    String end, String money, String time){
        return "<html lang=\"en\">\n" +
                "  <body>\n" +
                "      <div style=\"width:960px;margin:0 auto;padding:10px;display:flex;align-items:center;color:#000\">\n" +
                "          <div style=\"width: 100%;\">\n" +
                "            <div style=\"text-align:center;font-size:24px;font-weight:bold;color:red\">Order Receipt</div>\n" +
                "            <div style=\"display:flex;align-items:center;justify-content:space-around;width:100%;height:40px;\">\n" +
                "              <div>\n" +
                "                <span>" + year + "</span>\n" +
                "                <span style=\"color:red\">Year</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
                "                <span>" + month + "</span>\n" +
                "                <span style=\"color:red\">Month</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
                "                <span>" + day + "</span>\n" +
                "                <span style=\"color:red\">Day</span>\n" +
                "              </div>\n" +
                "              <div>\n" +
                "                Order Number:\n" +
                "                <span style=\"color:#9C5223;font-weight:bold\">" + id + "</span>\n" +
                "              </div>\n" +
                "            </div>\n" +
                "      \n" +
                "            <div style=\"display:flex;align-items:center\">\n" +
                "              <table border=\"1\" style=\"width: 95%; border-collapse: collapse;border:1px solid red\">\n" +
                "                <tr class=\"tempTr\">\n" +
                "                  <td style=\"color:red;width: 20%;\" >Name</td>\n" +
                "                  <td  style=\"width: 30%;\">" + name + "</td>\n" +
                "                  <td style=\"color:red;width: 20%;\">Phone Number</td>\n" +
                "                  <td colspan=\"3\" style=\"width: 30%;\">" + phone +"</td>\n" +
                "                </tr>\n" +
                "                <tr class=\"tempTr\">\n" +
                "                  <td style=\"width: 10%;color:red\">Username</td>\n" +
                "                  <td style=\"width: 20%;color:red\">Venue Name</td>\n" +
                "                  <td style=\"width: 10%;color:red\">Location</td>\n" +
                "                  <td style=\"width: 10%;color:red\">Start Time</td>\n" +
                "                  <td style=\"width: 10%;color:red\">End Time</td>\n" +
                "                  \n" +
                "                </tr>\n" +
                "                <tr class=\"tempTr\">\n" +
                "                  <td>" + username + "</td>\n" +
                "                  <td>" + venueName + "</td>\n" +
                "                  <td>" + address + "</td>\n" +
                "                  <td>" + begin + "</td>\n" +
                "                  <td>" + end + "</td>\n" +
                "                  \n" +
                "                </tr>\n" +
                "              \n" +
                "\n" +
                "                <tr class=\"tempTr\" style=\"line-height:30px\">\n" +
                "                  <td colspan=\"8\">\n" +
                "                    <span style=\"color:red\">Money:<br></span>\n" +
                "                    <span>" + money + " CNY</span>\n" +
                "                  \n" +
                "                </tr>\n" +
                "                <tr class=\"tempTr\">\n" +
                "                  <td style=\"color:red\" colspan=\"2\">Order Time</td>\n" +
                "                  <td colspan=\"6\">" + time + "</td>\n" +
                "                </tr>\n" +
                "              </table>\n" +
                "             \n" +
                "            </div>\n" +
                "            <div style=\"width:95%;display:flex;height:40px;line-height:40px;color:red\">\n" +
                "              <div style=\"width:33.33%;padding-left:10px\">\n" +
                "                Payee:\n" +
                "                <span style=\"color:#000\">Sports Center</span>\n" +
                "              </div>\n" +
                "              \n" +
                "            </div>\n" +
                "          </div>\n" +
                "        </div>\n" +
                "  </body>\n" +
                "  <style scoped>\n" +
                "      .tempTr td {\n" +
                "        height: 30px;\n" +
                "        text-align: center;\n" +
                "      }\n" +
                "  </style>\n" +
                "</html>";
    }


    public static String codeEmail(String code){
        return "<html >\n" +
                "\n" +
                "<body>\n" +
                "<div class=\"header\">\n" +
                "    <div style=\"padding: 10px;padding-bottom: 0px;\">\n" +
                "        <p style=\"margin-bottom: 10px;padding-bottom: 0px;\">Dear user, hello:</p>\n" +
                "        <p style=\"text-indent: 2em; margin-bottom: 10px;\">You are applying for email verification, and your verification code is:</p>\n" +
                "        <p class=\"code-text\">" + code +"</p>\n" +
                "        <div class=\"footer\">\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</div>\n" +
                "</body>\n" +
                "<style lang=\"css\">\n" +
                "    body {\n" +
                "        margin: 0px;\n" +
                "        padding: 0px;\n" +
                "        font: 100% SimSun, Microsoft YaHei, Times New Roman, Verdana, Arial, Helvetica, sans-serif;\n" +
                "        color: #000;\n" +
                "    }\n" +
                "\n" +
                "    .header {\n" +
                "        height: auto;\n" +
                "        width: 820px;\n" +
                "        min-width: 820px;\n" +
                "        margin: 0 auto;\n" +
                "        margin-top: 20px;\n" +
                "        border: 1px solid #eee;\n" +
                "    }\n" +
                "\n" +
                "    .code-text {\n" +
                "        text-align: center;\n" +
                "        font-family: Times New Roman;\n" +
                "        font-size: 22px;\n" +
                "        color: #C60024;\n" +
                "        padding: 20px 0px;\n" +
                "        margin-bottom: 10px;\n" +
                "        font-weight: bold;\n" +
                "        background: #ebebeb;\n" +
                "    }\n" +
                "\n" +
                "    .footer {\n" +
                "        margin: 0 auto;\n" +
                "        z-index: 111;\n" +
                "        width: 800px;\n" +
                "        margin-top: 30px;\n" +
                "        border-top: 1px solid #DA251D;\n" +
                "    }\n" +
                "</style>\n" +
                "</html>";
    }


    public static String itemOrderEmail(String year, String month, String day, String name, String phone,
                                        String username, String itemName, int number,
                                        String money, String time){
        return "<html lang=\"en\">\n" +
                "  <body>\n" +
                "      <div style=\"width:960px;margin:0 auto;padding:10px;display:flex;align-items:center;color:#000\">\n" +
                "          <div style=\"width: 100%;\">\n" +
                "            <div style=\"text-align:center;font-size:24px;font-weight:bold;color:red\">Item Order Receipt</div>\n" +
                "            <div style=\"display:flex;align-items:center;justify-content:space-around;width:100%;height:40px;\">\n" +
                "              <div>\n" +
                "                <span>" + year + "</span>\n" +
                "                <span style=\"color:red\">Year</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
                "                <span>" + month + "</span>\n" +
                "                <span style=\"color:red\">Month</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
                "                <span>" + day + "</span>\n" +
                "                <span style=\"color:red\">Day</span>\n" +
                "              </div>\n" +
                "              <div>\n" +
                "                <span style=\"color:#9C5223;font-weight:bold\"></span>\n" +
                "              </div>\n" +
                "            </div>\n" +
                "      \n" +
                "            <div style=\"display:flex;align-items:center\">\n" +
                "              <table border=\"1\" style=\"width: 95%; border-collapse: collapse;border:1px solid red\">\n" +
                "                <tr class=\"tempTr\">\n" +
                "                  <td style=\"color:red;width: 20%;\" >Name</td>\n" +
                "                  <td  style=\"width: 30%;\">" + name + "</td>\n" +
                "                  <td style=\"color:red;width: 20%;\">Phone Number</td>\n" +
                "                  <td colspan=\"3\" style=\"width: 30%;\">" + phone + "</td>\n" +
                "                </tr>\n" +
                "                <tr class=\"tempTr\">\n" +
                "                  <td style=\"width: 10%;color:red\">Username</td>\n" +
                "                  <td style=\"width: 20%;color:red\">Item Name</td>\n" +
                "                  <td style=\"width: 10%;color:red\">Number</td>\n" +
                "                  \n" +
                "                </tr>\n" +
                "                <tr class=\"tempTr\">\n" +
                "                  <td>" + username + "</td>\n" +
                "                  <td>" + itemName + "</td>\n" +
                "                  <td>" + number + "</td>              \n" +
                "                </tr>\n" +
                "              \n" +
                "\n" +
                "                <tr class=\"tempTr\" style=\"line-height:30px\">\n" +
                "                  <td colspan=\"8\">\n" +
                "                    <span style=\"color:red\">Money:<br></span>\n" +
                "                    <span>" + money + " CNY</span>\n" +
                "                  \n" +
                "                </tr>\n" +
                "                <tr class=\"tempTr\">\n" +
                "                  <td style=\"color:red\" colspan=\"2\">Order Time</td>\n" +
                "                  <td colspan=\"6\">" + time + "</td>\n" +
                "                </tr>\n" +
                "              </table>\n" +
                "             \n" +
                "            </div>\n" +
                "            <div style=\"width:95%;display:flex;height:40px;line-height:40px;color:red\">\n" +
                "              <div style=\"width:33.33%;padding-left:10px\">\n" +
                "                Payee:\n" +
                "                <span style=\"color:#000\">Sports Center</span>\n" +
                "              </div>\n" +
                "              \n" +
                "            </div>\n" +
                "          </div>\n" +
                "        </div>\n" +
                "  </body>\n" +
                "  <style scoped>\n" +
                "      .tempTr td {\n" +
                "        height: 30px;\n" +
                "        text-align: center;\n" +
                "      }\n" +
                "  </style>\n" +
                "</html>";
    }

}
