//package controller;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import model.*;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import dao.ProductDAO;
//import model.Cart;
//
///**
// * Servlet implementation class CartController
// */
//@WebServlet("/add-to-cart")
//public class AddToCartController extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//
//	/**
//	 * @see HttpServlet#HttpServlet()
//	 */
//	public AddToCartController() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
//	 *      response)
//	 */
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//	    resp.setContentType("text/html;charset=UTF-8");
//	    ProductDAO productDAO = new ProductDAO()	;
//	    int id = Integer.parseInt(req.getParameter("id"));
//	    String color = req.getParameter("colorId");
//	    Cart cart = new Cart();
//	    cart.setId(id);
//	    if(color !=null) cart.addColor(color);
//	    else {
//	    	cart.addColor("Blue");
//		}
//	    cart.setQuantity(1);
//
//	    HttpSession session = req.getSession();
//	    Account account = (Account) session.getAttribute("username");
// 	    ArrayList<Cart> cart_list = new ArrayList<Cart>();
// 	   int userId=-1;
//	    if(account!=null) {
//        	session.removeAttribute("carts");
//	    	 userId =  account.getId();
//	 	    cart_list= (ArrayList<Cart>)productDAO.getCartByUserId(userId);
//	 	    System.out.println("user id :"+userId);
////        	session.removeAttribute("carts");
//	 	    session.setAttribute("carts", cart_list);
//	    }else {
//		    cart_list = (ArrayList<Cart>) session.getAttribute("carts");
//	    }
//	    if (cart_list == null) {
//	        cart_list = new ArrayList<Cart>();
//	        cart_list.add(cart);
//	        session.setAttribute("carts", cart_list);
//	    } else {
//	        boolean exist = false;
//	        for (Cart c : cart_list) {
//	            if (c.getId() == id) {
//	                int quantity = c.getQuantity();
//	                c.setQuantity(quantity + 1);
//	                exist = true;
//	                break;
//	            }
//	        }
//	        if (!exist) {
//	            cart_list.add(cart);
//	        }
//	        if(account!= null) {
//	        	productDAO.updateCartToDatabase(userId, cart_list);
//	        	session.removeAttribute("carts");
//	        	session.setAttribute("carts", cart_list);
//	        }
//	        
//	    }
//	    resp.sendRedirect("index");
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
//	 *      response)
//	 */
//
//
//	protected void doGet1(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//	    resp.setContentType("text/html;charset=UTF-8");
//	    ProductDAO productDAO = new ProductDAO()	;
//	    int id = Integer.parseInt(req.getParameter("id"));
//	    String color = req.getParameter("colorId");
//	    Cart cart = new Cart();
//	    cart.setId(id);
//	    if(color !=null) cart.addColor(color);
//	    else {
//	    	cart.addColor("Blue");
//		}
//	    cart.setQuantity(1);
//
//	    HttpSession session = req.getSession();
//	    Account account = (Account) session.getAttribute("username");
// 	    ArrayList<Cart> cart_list = new ArrayList<Cart>();
// 	   int userId=-1;
//	    if(account!=null) {
//	    	  userId =  account.getId();
//	 	    cart_list= (ArrayList<Cart>)productDAO.getCartByUserId(userId);
//	 	    System.out.println("user id :"+userId);
//	 	    session.setAttribute("carts", cart_list);
//	    }else {
//		    cart_list = (ArrayList<Cart>) session.getAttribute("carts");
//	    }
//	    if (cart_list == null) {
//	        cart_list = new ArrayList<Cart>();
//	        cart_list.add(cart);
//	        session.setAttribute("carts", cart_list);
//	    } else {
//	        boolean exist = false;
//	        for (Cart c : cart_list) {
//	            if (c.getId() == id) {
//	                int quantity = c.getQuantity();
//	                c.setQuantity(quantity + 1);
//	                exist = true;
//	                break;
//	            }
//	        }
//	        if (!exist) {
//	            cart_list.add(cart);
//	        }
////	        if(account!= null) productDAO.updateCartToDatabase(userId, cart_list);
//	    }
//	    if(account!= null) productDAO.updateCartToDatabase(userId, cart_list);
//	    resp.sendRedirect("index");
//	}
//	protected void doGet2(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setContentType("text/html;charset=UTF-8");
//        ProductDAO productDAO = new ProductDAO();
//        int id = Integer.parseInt(req.getParameter("id"));
//        String color = req.getParameter("colorId");
//        Cart cart = new Cart();
//        cart.setId(id);
//        if (color != null) {
//            cart.addColor(color);
//        } else {
//            cart.addColor("Blue");
//        }
//        cart.setQuantity(1);
//
//        HttpSession session = req.getSession();
//        Account account = (Account) session.getAttribute("username");
//        ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("carts");
//
//        if (account != null) {
//            int userId = account.getId();
//            if (cart_list == null) {
//                cart_list = new ArrayList<Cart>();
//            } else {
//                // Nếu người dùng đã đăng nhập và đã có giỏ hàng trong phiên, cần lấy danh sách
//                // giỏ hàng từ cơ sở dữ liệu để đồng bộ
//                cart_list = (ArrayList<Cart>) productDAO.getCartByUserId(userId);
//            }
//            session.setAttribute("carts", cart_list);
//        } else {
//            if (cart_list == null) {
//                cart_list = new ArrayList<Cart>();
//            }
//        }
//
//        boolean exist = false;
//        for (Cart c : cart_list) {
//            if (c.getId() == id) {
//                int newQuantity = c.getQuantity() + 1;
//                c.setQuantity(newQuantity);
//                exist = true;
//                break;
//            }
//        }
//
//        if (!exist) {
//            cart_list.add(cart);
//        }
//
//        if (account != null) {
//            int userId = account.getId();
//            productDAO.updateCartToDatabase(userId, cart_list);
//        }
//
//        resp.sendRedirect("index");
//    }
//
//    /**
//     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
//     *      response)
//     */
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
//	 *      response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}
//public static void main(String[] args) {
//	
//}
//}

package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Cart;
import model.Account;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ProductDAO;

@WebServlet("/add-to-cart")
public class AddToCartController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AddToCartController() {
        super();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        ProductDAO productDAO = new ProductDAO();
        int id = Integer.parseInt(req.getParameter("id"));
        String color = req.getParameter("color");
        Cart cart = new Cart();
        cart.setId(id);
        if (color != null) {
            cart.addColor(color);
        } else {
            cart.addColor("Blue");
        }
        cart.setQuantity(1);

        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("username");
        List<Cart> cart_list = new ArrayList<Cart>();

        if (account != null) {
            int userId = account.getId();
            cart_list = productDAO.getCartByUserId(userId);
            session.setAttribute("carts", cart_list);
        } else {
            cart_list = (List<Cart>) session.getAttribute("carts");
            if (cart_list == null) {
                cart_list = new ArrayList<Cart>();
                session.setAttribute("carts", cart_list);
            }
        }

        boolean exist = false;
        for (Cart c : cart_list) {
            if (c.getId() == id && c.getColors().contains(color)) {
                int newQuantity = c.getQuantity() + 1;
                c.setQuantity(newQuantity);
                exist = true;
                break;
            }
        }

        if (!exist) {
            cart_list.add(cart);
        }

        if (account != null) {
            int userId = account.getId();
            productDAO.updateCartToDatabase(userId, cart_list);
        }

        resp.sendRedirect("index");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
