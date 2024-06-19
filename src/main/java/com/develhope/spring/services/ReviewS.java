//package com.develhope.spring.services;
//
//import com.develhope.spring.models.ResponseModel;
//import org.springframework.beans.factory.annotation.Autowired;
//
//public class ReviewS {
//
//    ReviewDto{String id_review, String id_order, Rating rating, String comment}
//
//    @Autowired
//    OrderService orderService;
//    @Autowired
//    OrderMapper orderMapper;
//
//    public ResponseModel createReview (ReviewDto reviewDto){
//        OrderEntity order = orderService.getOrderEntityById(review.getId_order);
//
//        if (!(order.getStatus==CONSEGNATO) ){return new ResponseModel(ResponseCode.A).addMessageDetail("puoi lasciare una recensione solo se lo stato è consegnato")}
//
//        reviewDao.save(reviewMapper.toEntity(reviewDto));
//        OrderEnity orderWithreviewEntity = orderService.setReview(order, review);
//        orderWithreviewDto = orderorderMapper.toDto(orderWithreviewEntity);
//       return new ResponseModel(Resposnecode.B , orderWithreviewDto);
//
//    }
//
//    public ResponseModel getMeanReating (String restaurant_id){
//        List<OrderEntity> listadegliIddegliOrdini = orderService.getOrdersByRestaurantId(restaurant_id);
//
//        // prendi id_review da lista ordini e fai la media dei rating delle relative review
//            public int getNumber(Rating rating){
//            switch (rating) {
//                case ONE:
//            }
//        }
//    }
//}
