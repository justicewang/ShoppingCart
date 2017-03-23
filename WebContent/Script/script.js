
$(document).ready(function(){

});
//---------------index.jsp-------------------------------
// add to cart function
$(document).on("click",".addtocart",function(){
	var id = $(this).parents('tr').attr('id');
	window.location.href = "ProductController?action=AddtoCart&id="+id;
});
//add to wish function
$(document).on("click",".addtowish",function(){
	var id = $(this).parents('tr').attr('id');
	window.location.href = "WishlistController?action=AddtoWish&id="+id;
});
//---------------WishList.jsp-------------------------------
//add to cart from wish function
$(document).on("click",".wishaddtocart",function(){
	var id = $(this).parents('tr').attr('id');
	window.location.href = "ProductController?action=WishAddtoCart&id="+id;
});
//Remove from WishList
$(document).on("click",".removefromwish",function(){
	var id = $(this).parents('tr').attr('id');
	window.location.href = "WishlistController?action=RemovefromWish&id="+id;
});
//---------------Cart.jsp-------------------------------
//Remove from Cart
$(document).on("click",".removefromcart",function(){
	var id = $(this).parents('tr').attr('id');
	window.location.href = "ProductController?action=RemovefromCart&id="+id;
});