package misc.Test2;

import main.Smash;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Pretty much a refactored version of JeremysBox2DTest (on creation)
 */
public class Box2DTest2 implements Screen {
    private World world;
    private Box2DDebugRenderer debugRenderer;
    private OrthographicCamera camera;
    private NewPlayer p1, p2;
    private TestStage stage;
    private Smash game;

    public Box2DTest2(Smash gameRef) {
	game = gameRef;

	world = new World(new Vector2(0, -9.81f), true);
	debugRenderer = new Box2DDebugRenderer();

	camera = new OrthographicCamera();

	p1 = new NewPlayer(world, 0, 5);
	p2 = new NewPlayer(world, 2, 5);

	stage = new TestStage(world);
    }

    @Override
    public void render(float delta) {
	Gdx.gl.glClearColor(0, 0, 0, 1);
	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

	getInput();

	world.step(delta, 8, 3);

	camera.update();

	debugRenderer.render(world, camera.combined);
    }

    private void getInput() {
	if (Gdx.input.isKeyJustPressed(Keys.ESCAPE))
	    game.setScreen(Smash.menu);
	if (Gdx.input.isKeyJustPressed(Keys.UP))
	    p1.jump();
	if (Gdx.input.isKeyPressed(Keys.RIGHT)
		&& !Gdx.input.isKeyPressed(Keys.LEFT))
	    p1.right();
	if (Gdx.input.isKeyPressed(Keys.LEFT)
		&& !Gdx.input.isKeyPressed(Keys.RIGHT))
	    p1.left();
	if (Gdx.input.isKeyPressed(Keys.DOWN))
	    p1.down();
	if (Gdx.input.isKeyJustPressed(Keys.W))
	    p2.jump();
	if (Gdx.input.isKeyPressed(Keys.D)
		&& !Gdx.input.isKeyPressed(Keys.A))
	    p2.right();
	if (Gdx.input.isKeyPressed(Keys.A)
		&& !Gdx.input.isKeyPressed(Keys.D))
	    p2.left();
	if (Gdx.input.isKeyPressed(Keys.S))
	    p2.down();
    }

    @Override
    public void show() {
	Gdx.input.setInputProcessor(Smash.inputProcessor);
    }

    @Override
    public void resize(int width, int height) {
	camera.viewportWidth = width / 20;
	camera.viewportHeight = height / 20;
	camera.update();
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
	world.dispose();
	debugRenderer.dispose();
    }
}