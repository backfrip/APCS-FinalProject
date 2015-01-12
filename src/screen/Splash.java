package screen;

import main.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import aurelienribon.tweenengine.TweenManager;
import aurelienribon.tweenengine.Tween;
import java.util.Random;

public class Splash implements Screen {
    private SpriteBatch batch;
    private Sprite splash;
    private Smash game;
    private OrthographicCamera camera;
    private TweenManager tweenManager;
    private Random rand;
    private String filename;
    private boolean secret;
    private Music theme;
    private float d;
    public Splash(Smash gameRef) {
	rand = new Random();
	secret = (rand.nextInt(200) == 0);
	game = gameRef;
	camera = new OrthographicCamera();
	camera.setToOrtho(false, 1280, 720);
	batch = new SpriteBatch();
	d=0;
	if(secret){
	    splash = new Sprite(new Texture(new FileHandle("resource/splash/splash-secret.png")));
	    theme = Gdx.audio.newMusic(new FileHandle("resource/sound/music/splash-secret.wav"));
	}else{
	    splash = new Sprite(new Texture(new FileHandle("resource/splash/splash.png")));
	    theme = Gdx.audio.newMusic(new FileHandle("resource/sound/music/splash-secret.wav"));//placeholder until we get a real theme
	}
	splash.setScale((float)1);
	splash.setPosition((float)0,(float)0);
    }


    @Override
    public void render(float delta) {
	Gdx.gl.glClearColor(0, 0, 0, 1);
	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

	batch.setProjectionMatrix(camera.combined);
	batch.begin();
	splash.draw(batch);
	batch.end();
	tweenManager.update(delta);
	if(!theme.isPlaying()){
	    Tween.to(splash,SpriteAccessor.ALPHA, 2).target(0).start(tweenManager);
	    d+=delta;
	}
	if(d>3){
	    game.setScreen(new Title(game));
	}
    }

    @Override
    public void show() {
	tweenManager = new TweenManager();
	Tween.registerAccessor(Sprite.class, new SpriteAccessor());	
	Tween.set(splash, SpriteAccessor.ALPHA).target(0).start(tweenManager);
	Tween.to(splash, SpriteAccessor.ALPHA, 2).target(1).start(tweenManager);
	theme.play();
    }

    @Override
    public void dispose() {
	batch.dispose();
	//splash.dispose();
	game.dispose();
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resize(int wdith, int height) {
    }

    @Override
    public void resume() {
    }
}
