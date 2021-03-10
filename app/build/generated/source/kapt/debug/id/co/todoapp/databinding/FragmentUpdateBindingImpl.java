package id.co.todoapp.databinding;
import id.co.todoapp.R;
import id.co.todoapp.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentUpdateBindingImpl extends FragmentUpdateBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = null;
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentUpdateBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds));
    }
    private FragmentUpdateBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.EditText) bindings[3]
            , (android.widget.EditText) bindings[1]
            , (android.widget.Spinner) bindings[2]
            );
        this.etDescriptionCurrent.setTag(null);
        this.etTitleCurrent.setTag(null);
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.propertiesCurrent.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.args == variableId) {
            setArgs((id.co.todoapp.fragments.update.UpdateFragmentArgs) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setArgs(@Nullable id.co.todoapp.fragments.update.UpdateFragmentArgs Args) {
        this.mArgs = Args;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.args);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        id.co.todoapp.data.models.Priority argsCurrentItemPriority = null;
        java.lang.String argsCurrentItemTitle = null;
        id.co.todoapp.fragments.update.UpdateFragmentArgs args = mArgs;
        id.co.todoapp.data.models.TodoData argsCurrentItem = null;
        java.lang.String argsCurrentItemDescription = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (args != null) {
                    // read args.currentItem
                    argsCurrentItem = args.getCurrentItem();
                }


                if (argsCurrentItem != null) {
                    // read args.currentItem.priority
                    argsCurrentItemPriority = argsCurrentItem.getPriority();
                    // read args.currentItem.title
                    argsCurrentItemTitle = argsCurrentItem.getTitle();
                    // read args.currentItem.description
                    argsCurrentItemDescription = argsCurrentItem.getDescription();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.etDescriptionCurrent, argsCurrentItemDescription);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.etTitleCurrent, argsCurrentItemTitle);
            id.co.todoapp.fragments.BindingAdapters.parsePriority(this.propertiesCurrent, argsCurrentItemPriority);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): args
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}