package com.zhaops.productservice.product.api;

import com.zhaops.productservice.product.dto.ProductCommentDto;
import com.zhaops.productservice.product.dto.UserDto;
import com.zhaops.productservice.product.entity.Product;
import com.zhaops.productservice.product.entity.ProductComment;
import com.zhaops.productservice.product.repository.ProductCommentRepository;
import com.zhaops.productservice.product.repository.ProductRepository;
import com.zhaops.productservice.product.services.HelloServiceCommand;
import com.zhaops.productservice.product.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author SoYuan
 */
@RestController
@RequestMapping("/products")
public class ProductEndpoint {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductCommentRepository productCommentRepository;

    @Autowired
    private UserService userService;

    @Autowired
    @Qualifier(value = "restTemplate")
    private RestTemplate restTemplate;

    /**
     * 获取商品列表
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Product> list() {
        return this.productRepository.findAll();
    }


    /**
     * 获取用户
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public UserDto getUser(@PathVariable Long id) {
        HelloServiceCommand command = new HelloServiceCommand("getUser", id, this.restTemplate);
        return command.execute();
    }


    /**
     * 获取指定商品的评论列表
     *
     * @param id 商品的Id
     * @return
     */
    @RequestMapping(value = "/{id}/comments", method = RequestMethod.GET)
    public List<ProductCommentDto> comments(@PathVariable Long id) {
        List<ProductComment> commentList = this.productCommentRepository.findByProductIdOrderByCreated(id);
        if (null == commentList || commentList.isEmpty())
            return Collections.emptyList();

        List<ProductCommentDto> list = commentList.stream().map((comment) -> {
            ProductCommentDto dto = new ProductCommentDto(comment);
            Long productId = comment.getProductId();
            dto.setProduct(this.productRepository.getOne(productId));
            UserDto user = this.userService.load(comment.getAuthorId());
            dto.setAuthor(user);
            return dto;
        }).collect(Collectors.toList());

        return list;
    }

    /**
     * 通过RestTemplate加载评论作者的用户信息
     *
     * @param userId 用户Id
     * @return
     */
    protected UserDto loadUser(Long userId) {
        UserDto userDto = this.restTemplate.getForEntity("http://USERSERVICE/users/{id}", UserDto.class, userId).getBody();
        return userDto;
    }
}



