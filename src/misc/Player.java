package misc;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Player extends InputAdapter {

    private Body body;
    private Fixture fixture;
    public final float width, height;
    private Vector2 velocity = new Vector2();
    private float movementForce = 50;
    private int pNum;

    public Player(World world, float x, float y, float width, int player) {
	this.width = width;
	height = width * 2;

	BodyDef bodyDef = new BodyDef();
	bodyDef.type = BodyType.DynamicBody;
	bodyDef.position.set(x, y);
	bodyDef.fixedRotation = true;

	PolygonShape shape = new PolygonShape();
	shape.setAsBox(width / 2, height / 2);

	FixtureDef fixtureDef = new FixtureDef();
	fixtureDef.shape = shape;
	fixtureDef.restitution = .1f;
	fixtureDef.friction = .8f;
	fixtureDef.density = 3;

	body = world.createBody(bodyDef);
	fixture = body.createFixture(fixtureDef);
	pNum = player;
    }

    public void update() {
	body.applyForceToCenter(velocity, true);
    }

    @Override
    public boolean keyDown(int keycode) {
	switch (pNum) {
	case 1:
	    switch (keycode) {
	    case Keys.A:
		velocity.x = -movementForce;
		break;
	    case Keys.D:
		velocity.x = movementForce;
		break;
	    case Keys.W:
		// TODO check if he is touching the ground
		velocity.y = 500;
	    default:
		return false;
	    }
	    break;
	case 2:
	    switch (keycode) {
	    case Keys.LEFT:
		velocity.x = -movementForce;
		break;
	    case Keys.RIGHT:
		velocity.x = movementForce;
		break;
	    case Keys.UP:
		// TODO check if he is touching the ground
		velocity.y = 100;
	    default:
		return false;
	    }
	    break;
	}
	return true;
    }

    @Override
    public boolean keyUp(int keycode) {
	switch (pNum) {
	case 1:
	    if (keycode == Keys.A || keycode == Keys.D)
		velocity.x = 0;
	    if (keycode == Keys.W)
		velocity.y = 0;
	    else
		return false;
	    return true;
	case 2:
	    if (keycode == Keys.LEFT || keycode == Keys.RIGHT)
		velocity.x = 0;
	    if (keycode == Keys.UP)
		velocity.y = 0;
	    else
		return false;
	    return true;
	}
	return false;
    }

    public float getRestitution() {
	return fixture.getRestitution();
    }

    public void setRestitution(float restitution) {
	fixture.setRestitution(restitution);
    }

    public Body getBody() {
	return body;
    }

    public Fixture getFixture() {
	return fixture;
    }/*
      * @Override public boolean shouldCollide(Fixture fixtureA,Fixture
      * fixtureB){ if(fixtureA==fixture || fixtureB==fixture){ return
      * body.getLinearVelocity().y<0;//not a very good way to do one-way
      * platforms! } return false; }
      */
}
