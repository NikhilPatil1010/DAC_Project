package com.Imart.Controller;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public CartResponse add(@RequestBody AddToCartRequest req) {
        return cartService.addToCart(req.getProductId(), req.getQuantity());
    }

    @GetMapping
    public CartResponse viewCart() {
        return cartService.getCart();
    }

    @DeleteMapping("/remove/{productId}")
    public CartResponse remove(@PathVariable long productId) {
        return cartService.removeItem(productId);
    }

    @DeleteMapping("/clear")
    public void clear() {
        cartService.clearCart();
    }
}
