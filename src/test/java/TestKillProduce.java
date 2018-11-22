import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.hui.dto.Exposer;
import com.hui.dto.SeckillExecution;
import com.hui.service.SeckillService;



public class TestKillProduce {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

	@Test
	 public void executeSeckillProcedureTest() {
		// System.out.println("Ö´ÐÐ");
        long seckillId = 1001;
        long phone = 1368011101;
        //
        Exposer exposer = seckillService.exportSeckillUrl(seckillId);
       // System.out.println("Ö´ÐÐ");
        if (exposer.isExposed()) {
            String md5 = exposer.getMd5();
            SeckillExecution execution = seckillService.executeSeckillProcedure(seckillId, phone, md5);
            System.out.println(execution.getStateInfo());
        }
    }
}
