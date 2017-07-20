package se.voipbusiness.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class OrderSvcInvoker implements ItemWriter<SvcReq> {

    @Override
    public void write(List<? extends SvcReq> svcReqs) throws Exception {

        for (SvcReq svcReq : svcReqs) {

            //RestTemplate restTemplate = new RestTemplate();

            //ResponseEntity<SvcResp> respEntity = restTemplate.postForEntity("http://localhost:8080/phone/order", svcReq, SvcResp.class);

            //SvcResp resp = respEntity.getBody();
            SvcResp resp = new SvcResp();
            resp.setId(svcReq.getId());
            resp.setMessage(svcReq.getName());

            System.out.println("calling web service:" + resp);
        }

        System.out.println("Processed items:" + svcReqs.size());
    }

}