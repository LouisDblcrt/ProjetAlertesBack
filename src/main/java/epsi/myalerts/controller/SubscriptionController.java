package epsi.myalerts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import epsi.myalerts.exceptions.NotFoundException;
import epsi.myalerts.model.Subscription;
import epsi.myalerts.model.SubscriptionPK;
import epsi.myalerts.repository.SubscriptionRepository;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

	@Autowired
	SubscriptionRepository subscriptionRepository;
	
	@GetMapping("")
	public List<Subscription> getAllSubscriptions(){
		return subscriptionRepository.findAll();
	}

	@GetMapping("/{id_user}&{id_alert_owner}")
	public Subscription getSubscriptionById(@PathVariable(name="id_user")Integer idUser,@PathVariable(name="id_alert_owner")Integer idAlertOwner){
		SubscriptionPK subPK = new SubscriptionPK(idUser,idAlertOwner);
		return subscriptionRepository.findById(subPK).orElseThrow(NotFoundException::new);
	}
	
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Subscription createSubscription(@RequestBody Subscription subscription) {
		return subscriptionRepository.save(subscription);
	}
	
	@PutMapping("/{id_user}&{id_alert_owner}")
	public Subscription modifySubscription(@RequestBody Subscription subscription, @PathVariable(name="id_user") Integer idUser,@PathVariable(name="id_alert_owner") Integer idAlertOwner) {
		SubscriptionPK sub = new SubscriptionPK(idUser,idAlertOwner);
		return subscriptionRepository.findById(sub).map(subscriptionFind ->{
			subscriptionFind.setSubscriptionPK(sub);
			subscriptionFind.setDate_subscription(subscription.getDate_subscription());
			return subscriptionRepository.save(subscriptionFind);
		}).orElseGet(()->{
			return subscriptionRepository.save(subscription);
		});
	}
	@CrossOrigin
	@DeleteMapping("/{id_user}&{id_alert_owner}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteSubscription(@PathVariable(name="id_user")Integer idUser, @PathVariable(name="id_alert_owner")Integer idAlertOwner) {
		subscriptionRepository.deleteById(new SubscriptionPK(idUser,idAlertOwner));
	}
}
