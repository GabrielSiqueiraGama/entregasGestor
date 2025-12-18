package com.zhant.entregasGestor.services;

import com.zhant.entregasGestor.dto.DeliveryDTO;
import com.zhant.entregasGestor.dto.mapper.DeliveryMapper;
import com.zhant.entregasGestor.repositories.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DeliveryAnalyticsService {

    @Autowired
    private DeliveryRepository deliveryRepository;
    @Autowired
    private DeliveryMapper deliveryMapper;

    public Map<String, Integer> getDeliveryCountByNeighborhood(){
        List<DeliveryDTO> deliveries = deliveryRepository.findAll()
                .stream()
                .map(deliveryMapper::toDto)
                .collect(Collectors.toList());

        Map<String, Integer> neighborhoodCount = new HashMap<>();

        for(DeliveryDTO delivery: deliveries){
            String neighborhood = delivery.neighborhood();
            neighborhoodCount.put(neighborhood, neighborhoodCount.getOrDefault(neighborhood, 0) + 1);
        }
        return neighborhoodCount;
    }

}
