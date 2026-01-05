package com.zhant.entregasGestor.services;

import com.zhant.entregasGestor.dto.AnalyticsDTO;
import com.zhant.entregasGestor.models.Delivery;
import com.zhant.entregasGestor.repositories.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DeliveryAnalyticsService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    public Map<String, Integer> getDeliveryCountByNeighborhood(){
        List<Delivery> deliveries = deliveryRepository.findAll();

        Map<String, Integer> neighborhoodCount = new HashMap<>();

        for(Delivery delivery: deliveries){
            String neighborhood = delivery.getNeighborhood();
            neighborhoodCount.put(neighborhood, neighborhoodCount.getOrDefault(neighborhood, 0) + 1);
        }
        return neighborhoodCount;
    }
    
    public Map<String, AnalyticsDTO> getDeliveryStatsByNeighborhood() {
        List<Delivery> deliveries = deliveryRepository.findAll();
        int total = deliveries.size();

        Map<String, Integer> neighborhoodCount = new HashMap<>();

        for (Delivery delivery : deliveries) {
            String neighborhood = delivery.getNeighborhood();
            neighborhoodCount.put(neighborhood, neighborhoodCount.getOrDefault(neighborhood, 0) + 1);
        }

        Map<String, AnalyticsDTO> result = new HashMap<>();

        for (Map.Entry<String, Integer> entry : neighborhoodCount.entrySet()) {
            double percentage = (entry.getValue() * 100.0) / total;
            percentage = Math.round(percentage * 100.0) / 100.0;
            result.put(
                entry.getKey(),
                new AnalyticsDTO(entry.getValue(), percentage)
            );
        }

        return result;
    }


    public Map<String, Integer> getDeliveryCountByCourier(){
        List<Delivery> deliveries = deliveryRepository.findAll();

        Map<String, Integer> courierCount = new HashMap<>();

        for(Delivery delivery: deliveries){
            if(delivery.getCourier() != null) {
                String courier = delivery.getCourier().getName();
                courierCount.put(courier, courierCount.getOrDefault(courier, 0) + 1);
            }
        }
        return courierCount;
    }

    public Map<String, Integer> getDeliveryCountByVehicle(){
        List<Delivery> deliveries = deliveryRepository.findAll();

        Map<String, Integer> vehicleCount = new HashMap<>();

        for(Delivery delivery: deliveries){
            if(delivery.getVehicle() != null){
                String vehicle = delivery.getVehicle().getName();
                vehicleCount.put(vehicle, vehicleCount.getOrDefault(vehicle, 0) + 1);
            }
        }
        return vehicleCount;
    }

}
